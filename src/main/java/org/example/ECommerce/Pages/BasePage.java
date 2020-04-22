package org.example.ECommerce.Pages;

import org.example.ECommerce.Utilities.SeleniumWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

public class BasePage {
    WebDriver driver;
    SeleniumWait wait;
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        wait = new SeleniumWait(this.driver);
    }
    public void enterUrl(String url)
    {
        driver.get(url);
    }
    protected void click(WebElement element)
    {
        wait.waitForPageToLoadCompletely();
        wait.waitForElementToBeVisible(element);
        wait.waitForElementToBeClickable(element);
        element.click();

    }
    protected void sendKeys(WebElement element, String text)
    {
        wait.waitForElementToBeVisible(element);
        try
        {
            element.clear();
            element.sendKeys(text);
        }
        catch(Exception e)
        {
            element.sendKeys(text);
        }
    }
    protected void selectFirstOptionFromList(List<WebElement> elements)
    {
        wait.waitForElementToBeClickable(elements.get(0));
        click(elements.get(0));

    }
    public void log(String message)
    {
        Reporter.log(message);
    }

}
