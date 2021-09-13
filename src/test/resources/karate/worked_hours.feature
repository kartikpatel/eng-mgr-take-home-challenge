Feature: Worked Hours

  Background:
    Given url baseUrl

  Scenario: Get Worked Hours
    * def WorkedHoursDetails =
      """
        {
          id: '#number',
          date: '#regex \\d{4}-\\d{2}-\\d{2}',
          hours: '#number'
        }
      """

    Given path '/v1/users/1/worked_hours'
    When method GET
    Then status 200
    And match response == '#[] WorkedHoursDetails'

  Scenario: Post Worked Hours with Valid User Id

    Given path '/v1/users/1/worked_hours'
    And request '{"date": "2021-01-11","hours":5.24}'
    And header Content-Type = 'application/json'
    When method post
    Then status 201

  Scenario: Post Worked Hours with Invalid User Id

    Given path '/v1/users/99/worked_hours'
    And request '{"date": "2021-01-11","hours":5.24}'
    And header Content-Type = 'application/json'
    When method post
    Then status 404