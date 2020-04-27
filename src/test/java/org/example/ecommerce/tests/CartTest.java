package org.example.ecommerce.tests;

import static org.example.ecommerce.utilities.readers.YamlReader.getYamlValue;

import org.example.ecommerce.pages.HomePage;
import org.example.ecommerce.setup.TestSessionManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartTest {
    TestSessionManager testSession;

    @BeforeTest
    public void setUp() {
        testSession = new TestSessionManager();
        testSession.setDriver();
    }

    @Test
    public void addProductFromFeaturedCollection() {
        Assert.assertEquals( new HomePage(testSession.getDriver())
                .launch(getYamlValue("url"))
                .loginUsingPassword(getYamlValue("credentials.password"))
                .selectFromFeaturedCollection(getYamlValue("featuredCollection.productName"))
                .selectColor(getYamlValue("featuredCollection.color"))
                .selectSize(getYamlValue("featuredCollection.size"))
                .addToCart()
                .viewCart()
                .getProductTitle(),getYamlValue("featuredCollection.productName"));
    }
    @Test
    public void addDifferentSizeProductsToCartAndValidate() {

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
    @Test
    public void addProductToCartAndValidate() {
        Assert.assertEquals( new HomePage(testSession.getDriver())
                .launch(getYamlValue("url"))
                .loginUsingPassword(getYamlValue("credentials.password"))
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size"))
                .addToCart()
                .viewCart()
                .getProductTitle(),getYamlValue("productDetails.productName"));
    }
    @Test
    public void increaseQuantityAndValidateTotal() {
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
    public void tearDowm() {
        testSession.tearDown();
    }
}
