package org.example.ecommerce.setup;

import org.example.ecommerce.utilities.readers.YamlReader;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.example.ecommerce.utilities.readers.ConfigPropertyReader.getProperty;

public class TestSessionManager {
    public WebDriver driver;
    DriverFactory wd;
    YamlReader yaml;

    public TestSessionManager() {
        wd = new DriverFactory();
        yaml = new YamlReader("src/test/resources/Data/TestData.yml");
    }

    public void setDriver() {
        driver = wd.getDriver(getConfigurations());
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        driver.quit();
    }

    private Map<String, String> getConfigurations() {
        String[] configKeys = {"browserName", "seleniumserver", "seleniumserverhosturl"};
        Map<String, String> config = new HashMap<String, String>();
        for (String string : configKeys) {
            try {
                if (System.getProperty(string).isEmpty()) {
                    config.put(string, getProperty("src/main/resources/Config.properties", string));
                } else {
                    config.put(string, System.getProperty(string));
                }
            } catch (NullPointerException e) {
                config.put(string, getProperty("src/main/resources/Config.properties", string));
            }
        }
        return config;
    }


}
