@regression
Feature: Login

  @login
  Scenario Outline: Login successfully
    Given I had access the login page
    When I fill the field with "<username>" and "<password>" valid
    And I click on the "Login" button
    Then I should be redirected to the "<expected_page>" page

    Examples: 
      | username      | password     | expected_page |
      | standard_user | secret_sauce | Products      |

  @login
  Scenario Outline: Failed login
    Given I had access the login page
    When I fill the field with "<username>" and "<password>" invalid
    And I click on the "Login" button
    Then I should not be redirected to the page Products

    Examples: 
      | username      | password |
      | standard_user | wrong    |
      
   @login
   Scenario: Failed login with dynamic data
   Given I had access the login page
   When I fill the field with username and password dynamic
   Then I should not be redirected to the page Products
