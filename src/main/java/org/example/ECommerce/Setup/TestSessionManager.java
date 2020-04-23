package org.example.ECommerce.Setup;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.example.ECommerce.Utilities.Readers.ConfigPropertyReader.getProperty;

public class TestSessionManager {
    WebDriver driver;
    DriverFactory wd;

    public TestSessionManager()
    {
        wd = new DriverFactory();
    }
    public void setDriver()
    {
        driver = wd.getDriver(getConfigurations());

    }
    private Map<String,String> getConfigurations()
    {
        String[] configKeys = { "browserName", "seleniumserver","seleniumserverhosturl"};
        Map<String, String> config = new HashMap<String, String>();
        for (String string : configKeys) {
            try {
                if (System.getProperty(string).isEmpty())
                    config.put(string, getProperty("src/main/resources/Config.properties", string));
                else
                    config.put(string, System.getProperty(string));
            } catch (NullPointerException e) {
                config.put(string, getProperty("src/main/resources/Config.properties", string));
            }
        }
        return config;
    }
    public WebDriver getDriver()

    {
        return driver;
    }
    public void tearDown()
    {
        driver.quit();
    }

}
