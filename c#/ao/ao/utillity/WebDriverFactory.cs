using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ao
{
    public class WebDriverFactory
    {
        public IWebDriver _driver;

        public IWebDriver InitializeDriver()
        {
            _driver = new ChromeDriver(@"C:\Automation");
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            _driver.Manage().Window.Maximize();
            return _driver;
        }

        public void TearDown()
        {
            _driver.Quit();
            foreach (var process in Process.GetProcessesByName("chromedriver"))
            {
                process.Kill();
            }
        }
    }
}
