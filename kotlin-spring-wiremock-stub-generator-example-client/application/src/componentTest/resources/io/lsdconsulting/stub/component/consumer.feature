Feature: Wiremock stub from server

  Scenario: Client should return data from server's stub
    Given the client is ready to accept requests
    When a request is sent to the client
    Then the data from the server's stub is returned

  Scenario: Client should return error if no stub is set up
    When a request is sent to the client
    Then the HTTP status code INTERNAL_SERVER_ERROR is returned
