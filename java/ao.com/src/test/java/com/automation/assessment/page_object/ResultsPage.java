package com.automation.assessment.page_object;

import com.automation.assessment.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ResultsPage extends DriverManager {

    @FindBy(css = ".brands-container li a")
    private List<WebElement> brandsElements;

    @FindBy(css = ".lister__aside-filters")
    @CacheLookup
    private WebElement aSideFilterPanel;

    @FindBy(id = ".inner.icon-logo")
    private WebElement spinner;

    @FindBy(css = "h1[data-testid='lister-main-title-large']")
    private WebElement header;

    public String getHeader() {
        sleep(1000);
        return header.getText();
    }

    public void selectBrands(String brand) {
        scrollToElement(brandsElements.get(0));
        waitUntilElementsGreaterThan(By.cssSelector(".brands-container li"), 6);
        for (WebElement element : brandsElements) {
            boolean selectedBrands = brand.toLowerCase().equalsIgnoreCase(element.getAttribute("data-tag-name"));
            if (selectedBrands) {
                waitUntilElementClickable(element).click();
                break;
            }
        }
    }

    public void selectFilter(String filterValue, String filterCategory) {
        for (WebElement filter : aSideFilterPanel.findElements(By.cssSelector("div[data-facet-name='" + filterCategory + "'] .u-mr--micro"))) {
            boolean isFilterFound = waitUntilTextToBe(filter, filterValue)
                    .getText().startsWith(filterValue);
            scrollToElement(filter);
            if (isFilterFound) {
                (filter).click();
                break;
            }
        }
    }
}
