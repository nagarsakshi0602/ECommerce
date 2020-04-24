package org.example.ECommerce.Tests;

import org.example.ECommerce.Pages.HomePage;
import org.example.ECommerce.Setup.TestSessionManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.example.ECommerce.Utilities.Readers.YamlReader.getYamlValue;

public class AddProductFromFeaturedCollection {
    TestSessionManager testSession;

    @BeforeTest
    public void setUp()
    {
        testSession = new TestSessionManager();
        testSession.setDriver();

    }
    @Test
    public void addProductFromFeaturedCollection()
    {
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
    @AfterTest
    public void tearDowm()
    {
        testSession.tearDown();
    }
}
