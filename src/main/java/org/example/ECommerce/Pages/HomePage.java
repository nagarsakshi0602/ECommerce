package org.example.ECommerce.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(css = "div.password-login a")
    private WebElement password;
    @FindBy(id = "Password")
    private WebElement inputPassword;
    @FindBy(css = "button.btn.btn--narrow")
    private WebElement btnEnter;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public HomePage launch(String url)
    {
        enterUrl(url);
        return this;
    }
    public DashboardPage loginUsingPassword(String pass)
    {
        click(password);
        log("User clicked on Enter Password Button");
        sendKeys(inputPassword,pass);
        click(btnEnter);
        return new DashboardPage(driver);
    }
}
