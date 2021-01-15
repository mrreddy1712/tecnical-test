using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using OpenQA.Selenium.Support.UI;
using System;

namespace ao
{
   public class aoHomePage
    {
        IWebDriver _driver;
        [FindsBy(How = How.Id, Using = "siteSearch-input")]
        private IWebElement _searchbar;

        private const String PageUri = @"https://ao.com/";
        public aoHomePage(IWebDriver driver)
        {
            _driver = driver;
            _driver.Navigate().GoToUrl(PageUri);
            PageFactory.InitElements(_driver, this);
        }

        public void searchForProduct(String product)
        {
            _searchbar.SendKeys(product);
            _searchbar.SendKeys(Keys.Enter);
        }
    }
}
