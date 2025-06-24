package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC01 extends TestBase {
    @Test
    public void testcase1() {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();
        HomePage homePage = new HomePage();

        basePage.goToLoginPage();
        loginPage.login(Constants.validUserName, Constants.validPassword);

        boolean welcomeTextIsDisplayed = homePage.checkWelcomeTextAfterLoginSuccess(Constants.validUserName);
        Assert.assertTrue(welcomeTextIsDisplayed);
    }
}
