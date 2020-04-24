/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.ECommerce.Utilities.Listeners;



import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.example.ECommerce.Setup.TestSessionManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import static org.example.ECommerce.Utilities.Readers.ConfigPropertyReader.getProperty;



/*import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;*/




/**
 * 
 */
public class TakeScreenshot implements ITestListener {

    WebDriver  driver;
    String testname;
    String screenshotPath = "./screenshots";
    Screenshot fpScreenshot;

   

    
    public void takeScreenshot() {
        screenshotPath = (getProperty("screenshot-path") != null) ? getProperty("screenshot-path"): screenshotPath;
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

        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            String saveImgFile = /*System.getProperty("user.dir") + File.separator*/  screenshotPath
					+ File.separator + this.testname +"_"+date_time+".png";

            Reporter.log("Save Image File Path : " + saveImgFile, true);
            FileUtils.copyFile(scrFile, new File(saveImgFile));
			Reporter.log("<a href='"+ saveImgFile.replace("./screenshots", "./") + "'> <img src='"+ saveImgFile.replace("./screenshots", "./") + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void takeScreenShotOnException(ITestResult result) {
    	File screenshotFile ;

        //creating date format
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_a");
        Date date = new Date();
        String date_time = dateFormat.format(date);
        System.out.println(date_time);

        //Creating a folder path
		File file = new File(screenshotPath);
        boolean exists = file.exists();

        //if failure and screenshot set to true take screenshot
        try
			{
				if (!exists)
				{
		            new File(screenshotPath ).mkdir();
		        }
				String saveImgFile = /*System.getProperty("user.dir") + File.separator + */screenshotPath
	                   + File.separator + this.testname +"_"+ result.getName()+"_"+date_time+".png";
				
	            //creating file
	            screenshotFile = new File(saveImgFile);

	            //taking screenshot
	            Reporter.log("Started taking screenshot");
	            //fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(driver);
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	           	Reporter.log("Screenshot taken");
	            //writing screenshot to file          
				Screenshot fpScreenshot = null;
	            //taking screenshot       
	          try{
	        	   //fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	          }
	          catch(TimeoutException exp)
	          {
	        	  Reporter.log(exp.getMessage());
	          }
	           	ImageIO.write(fpScreenshot.getImage(),"PNG", screenshotFile);
	            Reporter.log("Screenshot successfully write in the file");
				Reporter.log("Screenshot taken");

				//logging in report
				Reporter.log("<a href='"+ saveImgFile.replace("./screenshots", "./") + "'> <img src='"+ saveImgFile.replace("./screenshots", "./") + "' height='100' width='100'/> </a>");
			} 
			
			catch (Exception e)
			{

	 				System.out.println("Exception while taking screenshot ");
	 						e.printStackTrace();

	 				Reporter.log("Exception while taking screenshot "+e.getMessage());

			} 

    }
    @Override
	public void onTestFailure(ITestResult result)
	{
		Field field ;
		this.testname = result.getTestClass().getRealClass().getSimpleName();
		TestSessionManager test = null;
		try {

			field = result.getTestClass().getRealClass().getDeclaredField("testSession");
			field.setAccessible(true);
			try {
				test = (TestSessionManager)field.get(result.getInstance());
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

