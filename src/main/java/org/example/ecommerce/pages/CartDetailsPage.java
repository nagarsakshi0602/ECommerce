package org.example.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartDetailsPage extends BasePage {

    @FindBy(css = ".cart__product-title")
    private List<WebElement> productTitle;

    @FindBy(xpath = "//h1[text()='Your cart']/following-sibling::a[contains(@class,'text-link')]")
    private WebElement continueShopping;

    public CartDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        for (WebElement element : productTitle) {
            return getText(element);
        }
        return null;
    }

    public Double getPriceOfProduct(String productTitle) {
        WebElement element = driver.findElement(
                By.xpath("//a[normalize-space(text())='" + productTitle + "']/ancestor::td/following-sibling::td//dd[@data-cart-item-regular-price]"));
        return Double.parseDouble(getText(element).trim().split(" ")[1].replace(",", ""));
    }

    public CartDetailsPage enterQuantityForProduct(String productTitle, int qty) {
        WebElement element = driver.findElement(
                By.xpath("//a[normalize-space(text())='" + productTitle + "']/ancestor::td/following-sibling::td//input[@class='cart__qty-input' and @name='updates[]']"));
        sendKeys(element, String.valueOf(qty));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getItemQuantity(String productTitle) {
        WebElement element = driver.findElement(
                By.xpath("//a[normalize-space(text())='" + productTitle + "']/ancestor::tr"));
        return element.getAttribute("data-cart-item-quantity");
    }

    public Double itemsActualTotal(String productTitle) {
        WebElement element = driver.findElement(
                By.xpath("//a[normalize-space(text())='" + productTitle + "']/ancestor::td/following-sibling::td[contains(@class,'cart__final-price')]//span"));
        return Double.parseDouble(getText(element).trim().split(" ")[1].replace(",", ""));
    }

    public Double itemsExpectedTotal(String productTitle) {
        return (getPriceOfProduct(productTitle) * Integer.parseInt(getItemQuantity(productTitle)));
    }

    public boolean compareThePriceAsPerQuantity(String productTitle) {
        boolean flag = false;
        if (itemsActualTotal(productTitle).equals(itemsExpectedTotal(productTitle))) {
            flag = true;
        } else {
            flag = false;
        }
        System.out.println(flag);
        return flag;
    }

    public ProductsListPage continueShopping() {
        click(continueShopping);
        return new ProductsListPage(driver);
    }

    public int getNoOfItemsInCart(String productTitle) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//a[contains(@class,'cart__product-title') and normalize-space(text())='" + productTitle + "']"));
        return elements.size();
    }
}
