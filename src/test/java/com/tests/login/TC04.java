package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04 extends TestBase {
    @Test
    public void testcase1() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        homePage.goToBookTicketPage();

        Assert.assertTrue(loginPage.checkLoginPageDisplayed());
    }
}
