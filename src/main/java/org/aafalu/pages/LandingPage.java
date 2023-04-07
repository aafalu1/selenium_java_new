package org.aafalu.pages;

import org.aafalu.base.AafaluBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LandingPage extends AafaluBase {


    public LandingPage(WebDriver driver) {
        super(driver);

    }

    private By emailField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    private By loginButton = By.id("login");

    public HomePage doLogin(String userEmeil, String pwd) {
        type(emailField, userEmeil);
        type(passwordField, pwd);
        clickAnWebElement(loginButton);
        return new HomePage(driver);
    }

}