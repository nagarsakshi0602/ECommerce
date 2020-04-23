package org.example.ECommerce.Tests;

import org.example.ECommerce.Setup.TestSessionManager;
import org.example.ECommerce.Utilities.Readers.YamlReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    TestSessionManager testSession;
    YamlReader yaml;
    @BeforeTest
    public void setUp()
    {
        testSession = new TestSessionManager();
        testSession.setDriver();
        yaml = new YamlReader("src/test/resources/TestData.yml");
    }
    @AfterTest
    public void tearDowm()
    {
        //testSession.tearDown();
    }
}
