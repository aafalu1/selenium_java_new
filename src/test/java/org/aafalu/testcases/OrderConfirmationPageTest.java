package org.aafalu.testcases;

import org.aafalu.base.AafaluBaseTest;
import org.aafalu.pages.HomePage;
import org.aafalu.pages.OrderConfirmationPage;
import org.aafalu.pages.PaymentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderConfirmationPageTest extends AafaluBaseTest {

    private HomePage hp;
    private PaymentPage pp;
    OrderConfirmationPage cp;

    @Test

    public void userSuccessfullyAddToProduct(){
        hp=landingPage.doLogin("aafalu@yahoo.com", "Aafalu#1");
        String productName = "zara coat 3";
        hp.clickOnAddtoCart(productName);
        pp=checkoutPage.clickOnCheckoutBtn();
        pp.selectCountryDropdown("united states");
        cp=pp.clickOnPlaceOrderLink();
        String actualConfirmationText=cp.getConfirmationMessageText();
        Assert.assertEquals("THANKYOU FOR THE ORDER.", actualConfirmationText);
    }
}
