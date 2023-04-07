package org.aafalu.base;


import org.aafalu.pages.LandingPage;
import org.aafalu.utilities.ConfigLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AafaluBase {

    protected WebDriver driver;
    protected WebDriverWait synchWait;
    public static ConfigLoader configLoader = ConfigLoader.getInstance();
    protected LandingPage landingPage;

    public AafaluBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement find(By byeEle) {
        waitForElementToLoad(byeEle);
        return driver.findElement(byeEle);
    }

    protected void waitForElementToLoad(By byEle) {
        synchWait = new WebDriverWait(driver, 20);
        synchWait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }

    protected void waitForElementToDisappear(By byEle) {
        synchWait = new WebDriverWait(driver, 20);
        synchWait.until(ExpectedConditions.invisibilityOfElementLocated(byEle));
    }

    /**
     * @param byEle
     * @param textToEnter
     */
    protected void type(By byEle, String textToEnter) {
        try {
            WebElement ele = find(byEle);
            ele.clear();
            ele.sendKeys(textToEnter);
        } catch (NoSuchElementException nsee) {
            System.out.println("Element not found: " + byEle.toString());
            nsee.printStackTrace();

        } catch (ElementNotInteractableException enie) {
            System.out.println("Element not interactable: " + byEle.toString());
            enie.printStackTrace();
        }

    }

    /**
     * @param byEle
     */
    protected void clickAnWebElement(By byEle) {
        try {
            WebElement ele = find(byEle);
            ele.click();
        } catch (NoSuchElementException nsee) {
            System.out.println("Element not found: " + byEle.toString());
            nsee.printStackTrace();

        } catch (ElementNotInteractableException enie) {
            System.out.println("Element not interactable: " + byEle.toString());
            enie.printStackTrace();
        }
    }

    /**
     * @param byEle
     * @return
     */

    protected String getElementText(By byEle) {
        try {
            WebElement ele = find(byEle);
            return ele.getText().trim();
        } catch (NoSuchElementException nsee) {
            System.out.println("Element not found: " + byEle.toString());
            nsee.printStackTrace();

        }
        return null;
    }

    /**
     * @param byEle
     * @return
     */

    protected String getElementAttribute(By byEle) {
        try {
            WebElement ele = find(byEle);
            return ele.getAttribute("value").trim();
        } catch (NoSuchElementException nsee) {
            System.out.println("Element not found: " + byEle.toString());
            nsee.printStackTrace();

        }
        return null;
    }

}