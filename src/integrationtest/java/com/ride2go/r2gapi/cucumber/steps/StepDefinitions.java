package com.ride2go.r2gapi.cucumber.steps;

import com.ride2go.r2gapi.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.when;

public class StepDefinitions extends SpringIntegrationTest {

    private static final String TRIP_BY_ID_PATH = "/trip/{tripId}";

    @LocalServerPort
    private int port;

    private String path;

    private Map<String, String> parameters;

    private ValidatableResponse response;

    @Given("path {string}")
    public void givenPath(String path) {
        parameters = new HashMap<>();
        this.path = path;
    }

    @Given("trip fetch by id endpoint")
    public void givenTripFetch() {
        givenPath(TRIP_BY_ID_PATH);
    }

    @And("trip id {}")
    public void givenId(String id) {
        parameters.put("tripId", id);
    }

    @When("I execute HTTP GET")
    public void whenExecute() {
        response = when().get(url(), parameters).then();
    }

    private String url() {
        return "http://localhost:" + port + path;
    }

    @Then("I should get HTTP {}")
    public void shouldReturnStatus(HttpStatus expectedStatus) {
        response.statusCode(expectedStatus.value());
    }

}
