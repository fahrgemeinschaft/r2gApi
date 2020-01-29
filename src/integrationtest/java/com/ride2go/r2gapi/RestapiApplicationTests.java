package com.ride2go.r2gapi;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {

    @ClassRule
    public static MySQLContainer<Ride2GoMySqlContainer> mySqlContainer = Ride2GoMySqlContainer.getInstance();

    @Test
    public void contextLoads() {
    }

}
