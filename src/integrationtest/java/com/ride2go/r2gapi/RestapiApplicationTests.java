package com.ride2go.r2gapi;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringIntegrationTest.class)
public class RestapiApplicationTests {

    @ClassRule
    public static MySQLContainer<Ride2GoMySqlContainer> mySqlContainer = Ride2GoMySqlContainer.getInstance();

    @ClassRule
    public static Ride2GoElasticSearchContainer elasticSearchContainer = Ride2GoElasticSearchContainer.getInstance();

    @Test
    public void contextLoads() {
    }

}