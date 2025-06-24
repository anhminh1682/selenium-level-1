package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();
        int n = 4;

        basePage.goToLoginPage();

        for (int i = 0; i < n; i++) {
            loginPage.login(Constants.validUserName, Constants.invalidPassword);
        }

        Assert.assertTrue(loginPage.checkLoginFailed(Constants.warningMessageLoginInvalidTooMuch));
    }
}
