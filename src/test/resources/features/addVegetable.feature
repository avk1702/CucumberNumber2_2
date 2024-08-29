@all
@addVegetable

Feature: Add Vegetable

  @correct
  Scenario: Add Non-Exotic Vegetable
    Given I am on the food page
    When I click the add button
    And I enter "Огурец" in the name field
    And I select "Овощ" from the type dropdown
    And I click the save button
    Then I should see "Список товаров" page
    And I should see "Огурец" in the product list1