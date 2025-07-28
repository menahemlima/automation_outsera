@regression
Feature: Checkout

@purchase
Scenario Outline: Purchase and Checkout Multiple Products
  Given I am logged on Swag Labs
  When I add "<product01>" and "<product02>" to the cart
  And I complete the checkout process with first name "<firstName>", last name "<lastName>" and zip code "<zipCode>"
  Then I should see the order confirmation message "<confirmationMessage>"

  Examples: Valid Purchases
    | product01            | product02              | firstName | lastName  | zipCode  | confirmationMessage        |
    | Sauce Labs Backpack  | Sauce Labs Bike Light  | Mary      | Test      | 889922   | Thank you for your order!  |