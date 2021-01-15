using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechTalk.SpecFlow;

namespace ao
{

    [Binding]
    public  class SearchitemSteps
    {
        private IWebDriver _driver;
        private WebDriverFactory _webDriverFactory;
        private aoHomePage _aoHomePage;
        private ResultsPage _ResultsPage;

        [Given(@"I am on ao home page")]
        public void GivenIAmOnAoHomePage()
        {
            _webDriverFactory = new WebDriverFactory();
            _driver = _webDriverFactory.InitializeDriver();
            _aoHomePage = new aoHomePage(_driver);
        }

        [When(@"I search for  ""(.*)""")]
        public void WhenISearchFor(string searchTerm)
        {
            _aoHomePage.searchForProduct(searchTerm);
        }

        [When(@"I select brand as ""(.*)""")]
        public void WhenISelectBrandAs(string brandName)
        {
            _ResultsPage = new ResultsPage(_driver);
            _ResultsPage.selectBrand(brandName);
        }

        [When(@"I apply ""(.*)"" filter from ""(.*)"" category")]
        public void WhenIApplyFilterFromCategory(string filterValue, string filterCategory)
        {
            _ResultsPage.selectFilter(filterValue, filterCategory);
        }

        [Then(@"I validate product are filtered accordingly")]
        public void ThenIValidateProductAreFilteredAccordingly()
        {
            _ResultsPage.Validatingtheresult();

            _webDriverFactory.TearDown();
        }
    }
}
