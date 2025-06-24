package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();

        basePage.clickToTab("Book ticket");

        Assert.assertTrue(loginPage.checkLoginPageDisplayed());
    }
}
