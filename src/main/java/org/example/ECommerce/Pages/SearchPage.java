package org.example.ECommerce.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(css = "div.product-card--list a")
    private List<WebElement> searchList;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public SearchPage selectFirstFromList()
    {
        for (WebElement element: searchList) {
            log("List has : "+ element.getText());
            System.out.println(element.getText());
        }
        selectFirstOptionFromList(searchList);
        return this;
    }
}
