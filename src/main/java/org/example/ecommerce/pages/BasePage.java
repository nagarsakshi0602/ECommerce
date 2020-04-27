package org.example.ecommerce.pages;

import org.example.ecommerce.utilities.selenium.SeleniumWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;

public class BasePage {
    WebDriver driver;
    SeleniumWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new SeleniumWait(this.driver);
    }

    public void enterUrl(String url) {
        driver.get(url);
    }

    public void log(String message) {
        Reporter.log(message);
    }

    protected void click(WebElement element) {
        wait.waitForPageToLoadCompletely();
        wait.waitForElementToBeVisible(element);
        wait.waitForElementToBeClickable(element);
        element.click();
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    protected void sendKeys(WebElement element, String text) {
        wait.waitForElementToBeVisible(element);
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            element.sendKeys(text);
        }
    }

    protected void selectFirstOptionFromList(List<WebElement> elements) {
        wait.waitForElementToBeClickable(elements.get(0));
        click(elements.get(0));
    }

    protected void selectFromDropDownByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }
}
