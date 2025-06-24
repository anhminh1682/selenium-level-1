package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02 extends TestBase {
    @Test
    public void testcase1() {
        LoginPage loginPage = new LoginPage();
        BasePage basePage = new BasePage();

        basePage.goToLoginPage();
        loginPage.login("", Constants.validPassword);

        Assert.assertTrue(loginPage.checkLoginFailed(Constants.errorMessageLoginWithBlankField));
    }
}
