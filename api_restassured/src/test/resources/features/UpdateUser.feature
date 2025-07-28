@regression
Feature: Update a user on Reqres API

  @update
  Scenario Outline: Update a new user with valid and invalid data
	  Given that the Reqres API is available
    When I update a user with id "<id>", "<name>", "<job>"
    Then the response should match with status code <status_code>
    And all information should be updated correctly
	  
    Examples: 
		  | id    | name | job   | status_code |
		  | 2     | John | owner | 200         |
		  | kd531 | Mary | ceo   | 400         |
		  
  @update @schema
  Scenario Outline: Validate endpoint schema update a user
	  Given that the Reqres API is available
    When I update a user with id "<id>", "<name>", "<job>"
    Then the schema should be equal "UpdateUserSchema.json"

    Examples:
		  | id | name | job   |
		  | 3  | John | owner |