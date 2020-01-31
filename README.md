# r2gApi
Ride2Go API for Ridesharing

## Integration testing

The integration tests can be run with the following command:

```shell script
./gradlew integrationTest
```

Note that the integration tests are separate from the unit tests in multiple ways:
- the unit tests are places in the `src/test` directory, the integration tests in `src/integrationtest`
- unit tests are tied to the build process, integration tests have to be run manually
- unit tests don't have any application environment (so they can be fast), integration tests have
dockerized MySQL and ElasticSearch

Therefore, every test that have external dependencies should go to the `src/integrationtest` directory.

### Environment

The application has two external dependencies:
- Elasticsearch
- MySQL

The testing environment starts up these dependencies using TestContainers and configures them with test
data.

#### MySQL

It's a version `5.7` MySQL instance. The initialization is done by the following scripts:
- `src/integrationtest/resources/schema.sql`: creates the required tables.
- `src/integrationtest/resources/data.sql`: inserts test data. Run after the previous script.

Spring runs these scripts automatically, so no further configuration is necessary.

The container initialization is done by the class `com.ride2go.r2gapi.Ride2GoMySqlContainer`. It sets
the required system properties, so the Spring application will connect to the container on startup.

Note that the application can be started multiple times during the testing. As a result, **the
initialization scripts will run multiple times on the same MySQL instance**. To make the tests as
isolated as possible, these scripts must ensure they initialize the database in the same way independent
of the fact that it's a newly started or a previously initialized database. The simplest way to do that
is to drop the tables before creating them in the `schema.sql` file.

#### Elasticsearch

The Elasticsearch instance is version `2.4.6`. It has two initialization scripts, too:
- The mapping initialization script comes from `ElasticConstants.mappingDefinitions()`. This class is
placed in the legacy repository.
- The test data is in `src/integrationtest/elastic-data.json`. These values will be inserted into the
`trips` index.

The container initialization is done by the class `com.ride2go.r2gapi.Ride2GoElasticSearchContainer`.
It sets the required system properties, so the Spring application will connect to the container on startup.

The data initialization is done by the `com.ride2go.r2gapi.ElasticSearchTestDataInitializer` class. To
deal with the problem of multiple initializations, this class drops the whole index before creating it.

### Writing tests

#### JUnit tests

We'll use `com.ride2go.r2gapi.RestapiApplicationTests` to introduce, how to write standard JUnit tests
with the configured environment:

```java
@RunWith(SpringRunner.class)
// 1
@ContextConfiguration(classes = SpringIntegrationTest.class)
public class RestapiApplicationTests {
    
    // 2
    @ClassRule
    public static MySQLContainer<Ride2GoMySqlContainer> mySqlContainer = Ride2GoMySqlContainer.getInstance();
    
    // 3
    @ClassRule
    public static Ride2GoElasticSearchContainer elasticSearchContainer = Ride2GoElasticSearchContainer.getInstance();

    @Test
    public void contextLoads() {
    }

}
```

At line `1` we reference the configuration class `com.ride2go.r2gapi.SpringIntegrationTest`, which
contains the necessary setup to boot up the application correctly.

Line `2` and `3` ensure that the MySQL and Elasticsearch containers boot up before the tests are run.

For performance reasons, these containers will be reused between tests.

#### Cucumber BDD tests

All configuration is already done to write Cucumber tests in this environment. These configurations can
be found in the class `com.ride2go.r2gapi.cucumber.CucumberTest`, which is very similar to a normal
JUnit test class.

The Cucumber step definitions are in the `com.ride2go.r2gapi.cucumber.steps.StepDefinitions` class.

The test cases should go in `.feature` files in the `src/integrationtest/resources/cucumber` folder.
