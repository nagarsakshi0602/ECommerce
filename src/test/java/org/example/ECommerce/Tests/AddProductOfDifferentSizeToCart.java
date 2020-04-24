package org.example.ECommerce.Tests;

import org.example.ECommerce.Pages.HomePage;
import org.example.ECommerce.Setup.TestSessionManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.example.ECommerce.Utilities.Readers.YamlReader.getYamlValue;

public class AddProductOfDifferentSizeToCart {

    TestSessionManager testSession;

    @BeforeTest
    public void setUp()
    {
        testSession = new TestSessionManager();
        testSession.setDriver();

    }
    @Test
    public void addProductToCartAndValidate()
    {

      Assert.assertEquals(  new HomePage(testSession.getDriver())
                .launch(getYamlValue("url"))
                .loginUsingPassword(getYamlValue("credentials.password"))
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size"))
                .addToCart()
                .selectSize(getYamlValue("anotherProduct.size"))
                .addToCart()
                .viewCart()
                .findNoOfItemsAddedInDifferentSize(getYamlValue("productDetails.productName")),2);


    }
    @AfterTest
    public void tearDowm()
    {
        testSession.tearDown();
    }

}
