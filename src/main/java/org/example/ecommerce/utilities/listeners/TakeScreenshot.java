/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.ecommerce.utilities.listeners;

import org.apache.commons.io.FileUtils;
import org.example.ecommerce.setup.TestSessionManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.example.ecommerce.utilities.readers.ConfigPropertyReader.getProperty;

public class TakeScreenshot implements ITestListener {

    WebDriver driver;
    String testname;
    String screenshotPath = "./screenshots";

    public void takeScreenshot() {
        screenshotPath = (getProperty("screenshot-path") != null) ? getProperty("screenshot-path") : screenshotPath;
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_a");
        Date date = new Date();
        String date_time = dateFormat.format(date);
        File file = new File(System.getProperty("user.dir") + File.separator
                + screenshotPath + File.separator + this.testname
                + File.separator + date_time);
        boolean exists = file.exists();
        if (!exists) {
            new File(screenshotPath).mkdir();
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String saveImgFile = screenshotPath
                    + File.separator + this.testname + "_" + date_time + ".png";
            Reporter.log("Save Image File Path : " + saveImgFile, true);
            FileUtils.copyFile(scrFile, new File(saveImgFile));
            Reporter.log("<a href='" + saveImgFile.replace("./screenshots", "./") + "'> <img src='" + saveImgFile.replace("./screenshots", "./") + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Field field;
        this.testname = result.getTestClass().getRealClass().getSimpleName();
        TestSessionManager test = null;
        try {
            field = result.getTestClass().getRealClass().getDeclaredField("testSession");
            field.setAccessible(true);
            try {
                test = (TestSessionManager) field.get(result.getInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        driver = test.getDriver();
        takeScreenshot();
    }
}

