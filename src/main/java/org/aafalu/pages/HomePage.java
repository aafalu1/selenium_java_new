package org.aafalu.pages;

import org.aafalu.base.AafaluBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AafaluBase {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By btnHome = By.xpath("//button[1][text()=' HOME ']");
    private By toastContainer = By.id("toast-container");
    private By animation = By.className("ng-animating");
    private By cartBtn = By.cssSelector("button[routerlink$='cart']");

    public boolean isHomeButtonDisplayed() {
        return find(btnHome).isDisplayed();

    }

    public void clickOnAddtoCart(String productName) {
        By addToCartBtn = By.xpath("//h5/b[text()='" + productName + "']/../../button[2]");
        clickAnWebElement(addToCartBtn);
        waitForElementToLoad(toastContainer);
        waitForElementToDisappear(animation);
        clickAnWebElement(cartBtn);
    }
}

