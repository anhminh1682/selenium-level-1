package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.LoginPage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        basePage.goToRegisterPage();
        registerPage.register(Constants.inActiveUsername, Constants.validPassword, Constants.validPassword, Constants.validPID);
        Assert.assertTrue(registerPage.checkRegisterSuccessWithValidInfor());
    }
}
