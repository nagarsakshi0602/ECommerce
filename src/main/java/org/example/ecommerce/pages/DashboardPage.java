package org.example.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    @FindBy(css = "button.site-header__search-toggle")
    private WebElement btnSearchBar;

    @FindBy(name = "q")
    private WebElement inputSearch;

    @FindBy(css = "button.search-bar__submit")
    private WebElement btnSearch;

    @FindBy(xpath = "//div[contains(@class,'section-header')]/h2")
    private WebElement featuredCollectionHeading;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchPage enterProductToSearch(String product) {
        click(btnSearchBar);
        sendKeys(inputSearch, product);
        click(btnSearch);
        return new SearchPage(driver);
    }

    public ProductDetailsPage selectFromFeaturedCollection(String productTitle) {
        scrollToElement(featuredCollectionHeading);
        WebElement element = featuredCollectionHeading.findElement(By.xpath("//a/span[text()='" + productTitle + "']/.."));
        click(element);
        return new ProductDetailsPage(driver);
    }
}
