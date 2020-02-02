package com.ride2go.r2gapi.cucumber.steps;

import com.ride2go.r2gapi.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions extends SpringIntegrationTest {

    private static final String TRIP_BY_ID_PATH = "/trip/{tripId}";

    @LocalServerPort
    private int port;

    private String path;
    private String requestBody;
    private ContentType requestType;

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

    @And("{} request body {string}")
    public void givenBody(ContentType requestType, String body) {
        this.requestType = requestType;
        this.requestBody = body;
    }

    @Given("trip search options {string}")
    public void givenSearchOptions(String options) {
        givenPath("/trip/search");
        givenBody(ContentType.JSON, options);
    }

    @When("I execute HTTP GET")
    public void whenExecuteGet() {
        response = given().get(url(), parameters).then();
    }

    @When("I fetch trip by id")
    public void whenExecuteFetchById() {
        whenExecuteGet();
    }

    @When("I execute HTTP POST")
    public void whenExecutePost() {
        RequestSpecification request = given();
        if (requestBody != null) {
            request = request.contentType(requestType).body(requestBody);
        }
        response = request.post(url(), parameters).then();
    }

    @When("I execute search")
    public void whenExecuteSearch() {
        whenExecutePost();
    }

    private String url() {
        return "http://localhost:" + port + path;
    }

    @Then("I should get HTTP {}")
    public void shouldReturnStatus(HttpStatus expectedStatus) {
        response.statusCode(expectedStatus.value());
    }

    @And("{} response type")
    public void shouldReturnContentType(ContentType contentType) {
        response.contentType(contentType);
    }

    @And("property {string} has string value {string}")
    public void shouldHavePropertyWithStringValue(String propertyPath, String value) {
        response.body(propertyPath, equalTo(value));
    }

    @And("property {string} has int value {}")
    public void shouldHavePropertyWithIntValue(String propertyPath, int value) {
        response.body(propertyPath, equalTo(value));
    }

    @And("property {string} has double value {} with max error {}")
    public void shouldHavePropertyWithDoubleValue(String propertyPath, double value, double maxError) {
        response.body(propertyPath, closeTo(value, maxError));
    }

}
