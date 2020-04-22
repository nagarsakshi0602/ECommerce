package org.example.ECommerce.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {
    WebDriverWait wait;
    public SeleniumWait(WebDriver driver)
    {
        wait = new WebDriverWait(driver,30);
    }
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBeInVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForPageToLoadCompletely()
    {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
