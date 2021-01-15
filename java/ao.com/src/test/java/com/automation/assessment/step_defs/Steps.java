package com.automation.assessment.step_defs;


import com.automation.assessment.page_object.HomePage;
import com.automation.assessment.page_object.ResultsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Steps {

    private ResultsPage resultsPage;
    private HomePage homePage;

    public Steps(HomePage homePage, ResultsPage resultsPage) {
        this.resultsPage = resultsPage;
        this.homePage = homePage;
    }

    @Given("^Iam on homepage$")
    public void iam_on_homepage() {
        assertThat("User not on Homepage.", homePage.isUserOnHomePage(), is(equalTo(true)));
    }

    @When("^I search for a product \"([^\"]*)\"$")
    public void i_search_for_a_product(String searchTerm) {
        this.homePage.searchForProduct(searchTerm);
    }

    @When("^I select brand as \"([^\"]*)\"$")
    public void i_select_brand_as(String brandName) {
        resultsPage.selectBrands(brandName);

    }

    @When("^I apply \"([^\"]*)\" filter from \"([^\"]*)\" category$")
    public void i_apply_option_from_filter(String filterValue, String filterCategory) {
        resultsPage.selectFilter(filterValue, filterCategory);
    }

    @Then("^I validate product are filtered accordingly$")
    public void i_validate_product_are_filtered_accordingly(List<String> expectedOutput) {
        String actualHeader = resultsPage.getHeader();
        expectedOutput.forEach(expected ->
                assertThat(actualHeader.toLowerCase(), containsString(expected.toLowerCase())));
    }
}
