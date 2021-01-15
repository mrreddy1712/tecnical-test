Feature: Filters
  As an end user
  I want to filter result of own choice
  So that i can find desired product easily

 Scenario: Search product with multiple filter applied
	Given I am on ao home page
  When  I search for  "Washing Machines"
	And I select brand as "beko"
	And I apply "Freestanding" filter from "Fit Type" category
  And I apply "Large" filter from "Wash Load" category
  Then I validate product are filtered accordingly
