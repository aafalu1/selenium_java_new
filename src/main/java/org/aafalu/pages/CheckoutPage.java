package org.aafalu.pages;

import org.aafalu.base.AafaluBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends AafaluBase {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By checkoutBtn = By.cssSelector(".totalRow button");

    public String getProductNameText(String productName) {
        By byProductName = By.xpath("//h3[text()='" + productName + "']");
        return getElementText(byProductName);
    }

    public PaymentPage clickOnCheckoutBtn() {
        clickAnWebElement(checkoutBtn);
        return new PaymentPage(driver);
    }

}

