package org.aafalu.testcases;

import org.aafalu.base.AafaluBaseTest;
import org.testng.annotations.Test;

public class PaymentPageTest extends AafaluBaseTest {

     @Test
     public void testConfirmationPage(){
         landingPage.doLogin("aafalu@yahoo.com", "Aafalu#1");
         String productName = "zara coat 3";
         homePage.clickOnAddtoCart(productName);
         checkoutPage.clickOnCheckoutBtn();
         paymentPage.selectCountryDropdown("united states");
         paymentPage.clickOnPlaceOrderLink();
     }
}
