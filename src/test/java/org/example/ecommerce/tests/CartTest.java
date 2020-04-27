package org.example.ecommerce.tests;

import org.example.ecommerce.pages.CartDetailsPage;
import org.example.ecommerce.pages.DashboardPage;
import org.example.ecommerce.pages.HomePage;
import org.example.ecommerce.setup.TestSessionManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.example.ecommerce.utilities.readers.YamlReader.getYamlValue;

public class CartTest {
    TestSessionManager testSession;
    DashboardPage dashboardPage;
    CartDetailsPage cartDetailsPage;

    @BeforeTest
    public void setUp() {
        testSession = new TestSessionManager();
        testSession.setDriver();
        dashboardPage = new HomePage(testSession.getDriver())
                .launch(getYamlValue("url"))
                .loginUsingPassword(getYamlValue("credentials.password"));

    }

    @Test
    public void addProductFromFeaturedCollection() {
        cartDetailsPage = dashboardPage
                .selectFromFeaturedCollection(getYamlValue("featuredCollection.productName"))
                .selectColor(getYamlValue("featuredCollection.color"))
                .selectSize(getYamlValue("featuredCollection.size"))
                .addToCart()
                .viewCart();
        Assert.assertEquals(cartDetailsPage
                .getProductTitle(), getYamlValue("featuredCollection.productName"));
    }

    @Test
    public void addDifferentSizeProductsToCartAndValidate() {

        cartDetailsPage = dashboardPage
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size1"))
                .addToCart()
                .selectSize(getYamlValue("productDetails.size2"))
                .addToCart()
                .viewCart();
        Assert.assertEquals(cartDetailsPage
                .findNoOfItemsAddedInDifferentSize(getYamlValue("productDetails.productName")), 2);
    }

    @Test
    public void addProductToCartAndValidate() {
        cartDetailsPage = dashboardPage
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size"))
                .addToCart()
                .viewCart();
        Assert.assertEquals(cartDetailsPage
                .getProductTitle(), getYamlValue("productDetails.productName"));
    }

    @Test
    public void increaseQuantityAndValidateTotal() {
        cartDetailsPage = dashboardPage
                .enterProductToSearch(getYamlValue("productDetails.productName"))
                .selectFirstFromList()
                .selectColor(getYamlValue("productDetails.color"))
                .selectSize(getYamlValue("productDetails.size"))
                .addToCart()
                .viewCart()
                .enterQuantityForProduct(getYamlValue("productDetails.productName"), 2);
        Assert.assertTrue(cartDetailsPage
                .compareThePriceAsPerQuantity(getYamlValue("productDetails.productName")));
    }

    @AfterTest
    public void tearDowm() {
        testSession.tearDown();
    }
}
