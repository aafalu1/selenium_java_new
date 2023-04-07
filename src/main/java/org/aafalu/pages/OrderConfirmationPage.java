package org.aafalu.pages;

import org.aafalu.base.AafaluBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends AafaluBase {
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    private By confirmationMessageText = By.xpath("//h1");

    public String getConfirmationMessageText() {
        return getElementText(confirmationMessageText);
    }

}

