package org.example.ECommerce.Tests;

import org.example.ECommerce.Pages.HomePage;
import org.example.ECommerce.Setup.TestSessionManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    TestSessionManager testSession;
    @BeforeTest
    public void setUp()
    {
       testSession = new TestSessionManager();
       testSession.setDriver();
    }
    @Test
    public void redirect()
    {
        new HomePage(testSession.getDriver())
                .launch("http://ecom-optimus.myshopify.com")
                .loginUsingPassword("idgad")
                .enterProductToSearch("RoundNeck Shirt")
                .selectFirstFromList();

    }

}
