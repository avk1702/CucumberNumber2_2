@all
@addFruit

Feature: Add Product

  @correct
  Scenario: Add Exotic Fruit
    Given I am on the food page
    When I click the add button
    And I enter "Авокадо" in the name field
    And I select "Фрукт" from the type dropdown
    And I check the exotic checkbox
    And I click the save button
    Then I should see "Список товаров" page
    And I should see "Авокадо" in the product list