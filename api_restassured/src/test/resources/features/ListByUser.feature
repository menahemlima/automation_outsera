@regression
Feature: List a user on Reqres API

  @list
  Scenario Outline: List a user by id
	  Given that the Reqres API is available
    When I list a user with id "<id>"
    Then the response should match with status code <status_code>
    And all information should be listed correctly "<email>", "<first_name>", "<last_name>", "<avatar>"
	  
    Examples: 
		  | id | email                  | first_name | last_name | avatar                                   | status_code |
		  | 2  | janet.weaver@reqres.in | Janet      | Weaver    | https://reqres.in/img/faces/2-image.jpg  | 200         |
		  | 10 | byron.fields@reqres.in | Byron      | Fields    | https://reqres.in/img/faces/10-image.jpg | 200         |
		  
  @list
  Scenario Outline: List a user by id invalid
	  Given that the Reqres API is available
    When I list a user with id "<id>"
    Then the user should not be listed with status code <status_code>
    And all information should not be visible
	  
    Examples: 
		  | id | status_code |
		  | 99 | 404         | 
		  
  @list @schema
  Scenario Outline: Validate endpoint schema list a user
	  Given that the Reqres API is available
    When I list a user with id "<id>"
    Then the schema should be equal "ListUserSchema.json"

    Examples: 
		  | id |
		  | 3  |