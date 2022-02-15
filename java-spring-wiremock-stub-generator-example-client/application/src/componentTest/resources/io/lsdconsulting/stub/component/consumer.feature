Feature: Wiremock stub from producer

  Scenario: Consumer should return data from producer's stub
    Given the consumer is ready to accept requests
    When a request is sent to the consumer
    Then the data from the producer's stub is returned

  Scenario: Consumer should return error if no stub is set up
    When a request is sent to the consumer
    Then the HTTP status code INTERNAL_SERVER_ERROR is returned
