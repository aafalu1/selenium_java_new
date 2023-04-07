package org.aafalu.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aafalu.pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AafaluBaseTest {
    protected WebDriver driver;
    protected AafaluBase testBase;
    protected LandingPage landingPage;
    protected HomePage homePage;
    protected CheckoutPage checkoutPage;
    protected PaymentPage paymentPage;
    protected OrderConfirmationPage orderConfirmationPage;
    private final String AUT_URL = "https://rahulshettyacademy.com/client";

    public WebDriver getDriver() {
        String browserName = AafaluBase.configLoader.getStringValue("browser");
        if ("chrome".equalsIgnoreCase(browserName)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if ("edge".equalsIgnoreCase(browserName)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @BeforeClass
    protected void launchApplication() {
        driver = getDriver();
        driver.get(AUT_URL);
        landingPage = new LandingPage(driver);
        homePage = new HomePage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    protected String takeScreenshot(String screenshotName, WebDriver driver) {

        String desPath = getScreenshotPath(screenshotName);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File(desPath);
        try {
            FileUtils.copyFile(src, des);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return desPath;
    }

    public String takeScreenshotInCaseOFTestFailure(WebDriver driver) {
        String screenshotBase64 = null;
        try {
            if (driver != null) {
                screenshotBase64 =
                        "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotBase64;
    }


    private String getScreenshotPath(String screenshotName) {
        String path = "";
        try {
            path = "./screenshots/" + screenshotName + ".jpg";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
