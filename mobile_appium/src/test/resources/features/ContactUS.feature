@regression
Feature: Contact us form

  @form
  Scenario Outline: Send a form to contact us
    Given on the Skill2Lead application home screen
    When I access the Contact us form
    And I submit the form with "<name>", "<email>", "<address>", "<mobile_number>"
    Then a success message "<expected_message>" should be displayed
    And the submitted information should be correctly displayed

    Examples: 
      | name     | email         | address           | mobile_number |
      | Fred App | test@test.com | Palace 123 Street | 9152-8563     |