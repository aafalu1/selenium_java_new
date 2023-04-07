package org.aafalu.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aafalu.utilities.App;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AddScreenshotsDemo {
   static WebDriver driver =null;


    public static void main(String[] args) throws IOException {
        WebDriverManager.chromiumdriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rahulshettyacademy.com/client");
        WebElement email= driver.findElement(By.id("userEmail"));
        WebElement password= driver.findElement(By.id("userPassword"));
        WebElement loginBtn= driver.findElement(By.id("login"));
        email.sendKeys("aafalu@yahoo.com");
        password.sendKeys("Aafalu#1");
        loginBtn.click();
        WebElement homeText= driver.findElement(By.xpath("//button[1][text()=' HOME ']"));
        Assert.assertEquals(homeText.getText(), "HOME");
        ExtentReports extentReports=new ExtentReports();
        File file=new File("report.html");
        ExtentSparkReporter sr=new ExtentSparkReporter(file);
        extentReports.attachReporter(sr);
        String path=takeScreenshots("login.jpg");
        ExtentTest test=extentReports.createTest("Screen Test 1", "This is adding screenshot");
        test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        extentReports.flush();
        driver.quit();
        Desktop.getDesktop().browse(new File("report.html").toURI());

    }

    private static String takeScreenshots(String screenshotName){

        File destFile = null;
        if(driver!=null){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
             destFile = new File("./scrrenshots/"+screenshotName);
            try{
                FileUtils.copyFile(srcFile,destFile);
            }catch (Exception e){
                e.getStackTrace();
            }

        }
        System.out.println("&&&&&&&&&&&&&&& "+ destFile.getAbsolutePath());
        return destFile.getAbsolutePath();
    }

}
