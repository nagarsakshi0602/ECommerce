package org.example.ECommerce.Tests;

import org.example.ECommerce.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.ECommerce.Utilities.Readers.YamlReader.getYamlValue;

public class IncreaseQuantityInCartTest extends BaseTest{

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
}
