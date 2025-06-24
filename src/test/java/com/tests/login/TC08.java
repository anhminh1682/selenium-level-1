package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.LoginPage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        String newEmail = Constants.inActiveUsername;

        // Register
        basePage.clickToTab("Register");
        registerPage.register(newEmail, Constants.validPassword, Constants.validPassword, Constants.validPID);
        Assert.assertTrue(registerPage.checkRegisterSuccessWithValidInfor());

        // Login
        basePage.clickToTab("Login");
        loginPage.login(newEmail, Constants.invalidPassword);

        Assert.assertTrue(loginPage.checkLoginFailed(Constants.errorMessageLoginWithInvalidFields));
    }
}
