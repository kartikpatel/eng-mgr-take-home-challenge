Feature: Users

  Background:
    Given url baseUrl

  Scenario: Get Users
    * def UserDetails =
      """
        {
          id: '#number',
          first_name: '#string',
          last_name: '#string',
          email: '#string'
        }
      """

    Given path '/v1/users'
    When method GET
    Then status 200
    And match response == '#[] UserDetails'