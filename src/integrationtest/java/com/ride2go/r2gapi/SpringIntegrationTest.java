package com.ride2go.r2gapi;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = RestapiApplication.class, loader = SpringBootContextLoader.class)
public class SpringIntegrationTest {

}
