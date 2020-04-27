package org.example.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage  extends BasePage{

    @FindBy(css = "h1.product-single__title")
    private WebElement productName;

    @FindBy(css= "div.product__price span.price-item--regular")
    private WebElement price;

    @FindBy(xpath = "//label[contains(text(),'Color')]/following-sibling::select")
    private WebElement color;

    @FindBy(xpath = "//label[contains(text(),'Size')]/following-sibling::select")
    private WebElement size;

    @FindBy(css = "button.product-form__cart-submit")
    private WebElement addToCart;

    @FindBy(css = "a.cart-popup__cta-link")
    private WebElement viewCart;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public ProductDetailsPage selectColor(String color) {
        selectFromDropDownByText(this.color,color);
        return this;
    }
    public ProductDetailsPage selectSize(String size) {
        selectFromDropDownByText(this.size,size);
        return this;
    }
    public ProductDetailsPage addToCart() {
        click(addToCart);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    public CartDetailsPage viewCart() {
        click(viewCart);
        return new CartDetailsPage(driver);
    }

}
