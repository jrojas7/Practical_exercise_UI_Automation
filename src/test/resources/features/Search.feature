@search-items @regression

Feature: Mercado Libre - Items search feature

  Background:
    Given I access the Mercado Libre Home page

  @items-page
  Scenario: Validate that it is possible to search for items
    When  I enter a specific item "balones futbol" in the item Search field
    And  I click on the Search icon
    Then I should be presented with the correct items list page

  @write-read-a-file
  Scenario: Validate that it is possible to save items information from the first 3 pages in a TXT file
    When I accept the cookies information popup
    And  I enter a specific item "camisetas" in the item Search field
    And  I click on the Search icon
    And I get the items information from the first three pages
    And I write and save the items information in a TXT file
    Then the information in the TXT file should be the same as the information captured from the 3 pages


