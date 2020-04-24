package org.example.ECommerce.Tests;

import org.example.ECommerce.Pages.HomePage;
import org.example.ECommerce.Setup.TestSessionManager;
import org.example.ECommerce.Utilities.Readers.YamlReader;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.example.ECommerce.Utilities.Readers.YamlReader.getYamlValue;

public class IncreaseQuantityInCartTest{


    TestSessionManager testSession;

    @BeforeTest
    public void setUp()
    {
        testSession = new TestSessionManager();
        testSession.setDriver();
        //yaml = new YamlReader("src/test/resources/Data/TestData.yml");
    }
    @Test
    public void increaseQuantityAndValidateTotal()
    {
        Assert.assertTrue(new HomePage(testSession.getDriver())
                .launch(getYamlValue("url"))
                .loginUsingPassword(getYamlValue("credentials.password"))
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size"))
                .addToCart()
                .viewCart()
                .enterQuantityForProduct(getYamlValue("productDetails.productName"),2)
                .compareThePriceAsPerQuantity(getYamlValue("productDetails.productName")));

    }
    @AfterTest
    public void tearDowm()
    {
        testSession.tearDown();
    }
}
