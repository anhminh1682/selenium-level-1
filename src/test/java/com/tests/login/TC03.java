package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03 extends TestBase {
    @Test
    public void testcase1() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        homePage.goToLoginPage();
        loginPage.login(Constants.validUserName, Constants.invalidPassword);

        Assert.assertTrue(loginPage.checkLoginFailed(Constants.errorMessageLoginWithBlankField));
    }
}
