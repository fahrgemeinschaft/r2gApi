Feature: Trip search

  Scenario: Existing id's are found
    Given trip fetch by id endpoint
    And trip id cd6c54ff-d387-0b64-fd5c-669e9ee570ff
    When I execute HTTP GET
    Then I should get HTTP OK

  Scenario: Not existing id's are not found
    Given trip fetch by id endpoint
    And trip id cd6c54ff-d387-0b64-fd5c-669e9ee570ab
    When I execute HTTP GET
    Then I should get HTTP NOT_FOUND