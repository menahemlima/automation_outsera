@regression
Feature: Delete a user on Reqres API

@delete
Scenario Outline: Delete a user with valid and invalid data
  Given that the Reqres API is available
  When I try to delete a user with id "<id>"
  Then the response should match with status code <status_code>

  Examples:
    | id    | status_code |
    | 2     | 204         |
    | ed89  | 400         |