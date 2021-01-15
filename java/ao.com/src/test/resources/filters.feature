@filters
Feature: Filters
  As an customer
  I want to filter the products of choice
  So that i can find respectively product with ease

  @filter-multi
  Scenario: Search product with multiple filter applied
    Given Iam on homepage
    When I search for a product "washing machine"
    And I select brand as "beko"
    And I apply "Large" filter from "Wash Load" category
    And I apply "Freestanding" filter from "Fit Type" category
    Then I validate product are filtered accordingly
      | Beko | Washing Machines | wash load |