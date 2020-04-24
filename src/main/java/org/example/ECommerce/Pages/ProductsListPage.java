package org.example.ECommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsListPage extends BasePage {


    public ProductsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public ProductDetailsPage selectProduct(String productTitle)
    {
        WebElement element = driver.findElement(By.xpath("//span[text()='"+productTitle+"']/ancestor::a"));
        click(element);
        return new ProductDetailsPage(driver);
    }
}
