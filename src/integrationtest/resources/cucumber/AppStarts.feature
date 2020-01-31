Feature: Can the application start?
  Check the actuator healthcheck endpoint

Scenario: Application starts
  Given path "/actuator/health"
  When I execute HTTP GET
  Then I should get HTTP OK