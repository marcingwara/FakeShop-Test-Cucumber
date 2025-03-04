Feature: Shopping Cart Functionality

  Scenario: Add a product to the cart
    Given I am on the homepage of the FakeStore website
    When I search for "Windsurfing w Lanzarote"
    And I click on the "Dodaj do koszyka" button
    Then the product "„Windsurfing w Lanzarote (Costa Teguise)“ został dodany do koszyka." should be added to my cart
    And the cart should show 1 item


