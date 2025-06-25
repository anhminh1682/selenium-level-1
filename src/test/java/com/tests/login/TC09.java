package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.ChangePasswordPage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC09 extends TestBase {
    @Test
    public void testcase1() {
        LoginPage loginPage = new LoginPage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        BasePage basePage = new BasePage();

        // Login
        basePage.clickToTab("Login");
        loginPage.login(Constants.validUserName, Constants.validPassword);

        // Change Password
        basePage.clickToTab("Change password");
        changePasswordPage.changePassword(Constants.validPassword, Constants.validPassword, Constants.validPassword);

        Assert.assertTrue(changePasswordPage.checkChangePasswordSuccessful());
    }
};
