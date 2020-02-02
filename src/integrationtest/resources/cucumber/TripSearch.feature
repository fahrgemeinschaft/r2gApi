Feature: Trip search

  Scenario: Existing id's are found
    Given trip fetch by id endpoint
    And trip id cd6c54ff-d387-0b64-fd5c-669e9ee570ff
    When I fetch trip by id
    Then I should get HTTP OK
    And JSON response type
    And property "id" has string value "cd6c54ff-d387-0b64-fd5c-669e9ee570ff"

  Scenario: Not existing id's are not found
    Given trip fetch by id endpoint
    And trip id cd6c54ff-d387-0b64-fd5c-669e9ee570ab
    When I fetch trip by id
    Then I should get HTTP NOT_FOUND

  Scenario: Search returns appropriate trips
    Given trip search options '{"page":{"page":0,"pageSize":5},"tripTypes":["OFFER","SEARCH"],"startPoint":{"location":{"longitude":8.7010592,"latitude":49.0362907},"radius":25},"endPoint":{"location":{"longitude":9.0044053,"latitude":48.7074558},"radius":25},"departure":{"time":"2013-07-03T08:00+0100","toleranceInDays":0},"reoccurDays":[],"smoking":"IRRELEVANT","animals":"IRRELEVANT","transportTypes":[],"baggage":"SMALL","gender":"IRRELEVANT","organizations":[]}'
    When I execute search
    Then I should get HTTP OK
    And JSON response type
    And property "page.pageSize" has int value 1
