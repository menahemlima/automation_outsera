@regression
Feature: Login on App

  @login
  Scenario Outline: Validate login flow with different credentials
    Given on the Skill2Lead application home screen
    When performs login with username "<user>" and password "<password>"
    Then the login result message "<message>" is displayed

    Examples: 
      | user            | password | message           |
      | admin@gmail.com | admin123 | Successfull login |
      | admin@gmail.com |   123456 | Wrong Credentials |

  @login
  Scenario: Send information about login
    Given I am logged on Skill2Lead app
    When I enter information into the 'John Constantine' field
    Then should be show a message with the previously entered value