using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ao
{
    public class ResultsPage
    {
        private readonly IWebDriver _driver;
        private readonly WebDriverWait wait;


        [FindsBy(How = How.Id, Using = "siteSearch-input")]
        private IWebElement _searchbar;
        [FindsBy(How = How.CssSelector, Using = ".brands-container li a")]
        private IList<IWebElement> _selectbrand;
        [FindsBy(How = How.CssSelector, Using = ".lister__aside-filters")]
        private IWebElement _aSideFilterPanel;
        [FindsBy(How = How.CssSelector, Using = "h1[data-testid='lister-main-title-large']")]
        private IWebElement _header;
        public ResultsPage(IWebDriver driver)
        {
            _driver = driver;
            wait = new WebDriverWait(_driver, TimeSpan.FromMinutes(1));
            PageFactory.InitElements(_driver, this);
        }


        public void selectBrand(string brand)
        {
            foreach (IWebElement element in _selectbrand)
            {
                Boolean selectedBrands = brand.ToLower().Equals(element.GetAttribute("data-tag-name"));
                if (selectedBrands)
                {
                    element.Click();
                    Thread.Sleep(2000);
                    _searchbar.SendKeys(Keys.PageDown);
                    break;
                }
            }
        }

        public void selectFilter(String filterValue, String filterCategory)
        {
            foreach (IWebElement filter in _aSideFilterPanel.FindElements(By.CssSelector("div[data-facet-name='" + filterCategory + "'] .u-mr--micro")))
            {
                Boolean isFilterFound = wait.Until(ExpectedConditions.TextToBePresentInElement(filter, filterValue));
                if (isFilterFound)
                {
                    (filter).Click();
                    Thread.Sleep(2000);
                    break;
                }
            }
        }

        public void Validatingtheresult()
        {
            var ExpectedHeader = "Beko Free Standing Washing Machines with 10 Kg, 10.5 Kg, 11 Kg, 12 Kg, 15 Kg wash load";
            var actualHeader = _header.Text;
            Assert.AreEqual(actualHeader.ToLower(), ExpectedHeader.ToLower());
        }
    }
}
