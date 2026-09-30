package org.aafalu.testcases;

import org.aafalu.base.AafaluBaseTest;
import org.aafalu.test.utilities.TestCredentials;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends AafaluBaseTest {
    @Test
    public void testLogin() {
        landingPage.doLogin(TestCredentials.email(), TestCredentials.password());
        Assert.assertTrue(homePage.isHomeButtonDisplayed());
    }
}
