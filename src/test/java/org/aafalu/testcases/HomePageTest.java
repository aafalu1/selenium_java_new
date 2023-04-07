package org.aafalu.testcases;

import org.aafalu.base.AafaluBaseTest;
import org.aafalu.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends AafaluBaseTest {
    HomePage hp;

    @Test
    public void testHomepage() {
        String productName = "zara coat 3";
        hp = landingPage.doLogin("aafalu@yahoo.com", "Aafalu#1");
        hp.clickOnAddtoCart(productName);
    }
}
