package org.aafalu.testcases;

import org.aafalu.base.AafaluBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends AafaluBaseTest {
    @Test
    public void testLogin() {
        landingPage.doLogin("aafalu@yahoo.com", "Aafalu#1");
        Assert.assertTrue(homePage.isHomeButtonDisplayed());
    }
}
