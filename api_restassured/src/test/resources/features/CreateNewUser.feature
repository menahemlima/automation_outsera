@regression
Feature: Create a new user on Reqres API

  @create
  Scenario Outline: Create a new user with valid and invalid data
	  Given that the Reqres API is available
    When I create a user with name "<name>" and job "<job>"
    Then the response should match with status code <status_code>
    And all data should be correctly
	  
    Examples: 
		  | name 		  | job       | status_code |
		  | testName  | manager   | 201         |	
 		  | *%@#$@&#@ | *%@#$@&#@ | 400         |	
	  
		  
  @create @schema
  Scenario Outline: Validate endpoint schema create a new user
	  Given that the Reqres API is available
    When I create a user with name "<name>" and job "<job>"
    Then the schema should be equal "CreateUserSchema.json"

    Examples: 
		  | name 		  | job        |
		  | PaulTest  | consultant |