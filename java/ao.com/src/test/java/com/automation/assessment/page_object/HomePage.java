package com.automation.assessment.page_object;

import com.automation.assessment.driver.DriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends DriverManager {

    @FindBy(css = "#siteSearch-input")
    private WebElement searchBox;

    public boolean isUserOnHomePage(){
       return driver.getCurrentUrl().endsWith("ao.com/");
    }

    public void searchForProduct(String product){
        searchBox.sendKeys(product);
        searchBox.sendKeys(Keys.ENTER);
    }
}
