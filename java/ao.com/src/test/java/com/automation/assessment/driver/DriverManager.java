package com.automation.assessment.driver;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    protected static WebDriver driver;
    private String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");

    public DriverManager() {
        PageFactory.initElements(driver, this);
    }

    public void openBrowser() {
        switch (browser) {
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
        }
    }

    public void maxBrowser() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }

    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void applyImplicit() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    protected WebElement waitUntilElementClickable(WebElement element) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void waitUntilElementsGreaterThan(By by, Integer noOfElements) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(by, noOfElements));
    }

    protected WebElement waitUntilTextToBe(WebElement element, String txt) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(element, txt));
        return element;
    }


    public void takeScreenShot(Scenario scenario) {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenShot, "image/png");
    }

    protected WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;

    }

    public void acceptCookies() {
        WebElement cookiesElement;
        try {
            cookiesElement = driver.findElement(By.cssSelector(".ao-cb__button--accept"));
            cookiesElement.click();

        } catch (NoSuchElementException ex) {
        }
    }
}
