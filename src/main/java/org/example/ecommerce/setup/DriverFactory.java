package org.example.ecommerce.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverFactory {
    WebDriver driver;

    protected WebDriver getDriver(Map<String,String> config)
    {
       if(config.get("seleniumserver").equalsIgnoreCase("local")) {
           if(config.get("browserName").equalsIgnoreCase("chrome")) {
            driver = getChromeDriver();
        } else if(config.get("browserName").equalsIgnoreCase("firefox")) {
            driver = getFirefoxDriver();
        }
       } else if(config.get("seleniumserver").equalsIgnoreCase("remote")) {
           driver = getRemoteDriver(config.get("seleniumserverhosturl"));
       }
       return driver;
    }

    private WebDriver getChromeDriver() {
        return new ChromeDriver();
    }
    private WebDriver getFirefoxDriver() {
         return new FirefoxDriver();
    }
    private WebDriver getRemoteDriver(String url) {
        WebDriver driver = null;
        try {
            DesiredCapabilities cap =new DesiredCapabilities();
            driver = new RemoteWebDriver(new URL(url),cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
