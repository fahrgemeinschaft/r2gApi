package com.ride2go.r2gapi.cucumber;

import com.ride2go.r2gapi.Ride2GoElasticSearchContainer;
import com.ride2go.r2gapi.Ride2GoMySqlContainer;
import com.ride2go.r2gapi.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/integrationtest/resources/cucumber")
@ContextConfiguration(classes = SpringIntegrationTest.class)
public class CucumberTest {

    @ClassRule
    public static MySQLContainer<Ride2GoMySqlContainer> mySqlContainer = Ride2GoMySqlContainer.getInstance();

    @ClassRule
    public static Ride2GoElasticSearchContainer elasticSearchContainer = Ride2GoElasticSearchContainer.getInstance();

}
