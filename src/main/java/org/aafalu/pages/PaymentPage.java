package org.aafalu.pages;

import org.aafalu.base.AafaluBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends AafaluBase {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }
    private By selectCountryField = By.cssSelector("input[placeholder='Select Country']");
    private By selectCountryLnk = By.xpath("//section/button[1]");
    private By placeOrderLink = By.xpath("//a[text()='Place Order ']");

    public void selectCountryDropdown(String countryName) {
        type(selectCountryField, countryName);
        clickAnWebElement(selectCountryLnk);
    }

    public OrderConfirmationPage clickOnPlaceOrderLink() {
        clickAnWebElement(placeOrderLink);
        return new OrderConfirmationPage(driver);
    }
}

