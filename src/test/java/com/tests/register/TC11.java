package com.tests.register;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        basePage.clickToTab("Register");
        registerPage.register(Constants.validUserName, "", "", "");

        Assert.assertTrue(registerPage.checkRegisterFailedWithInvalidPassword(Constants.errorRegisterWithInvalidPasswordLength));
        Assert.assertTrue(registerPage.checkRegisterFailedWithInvalidPID(Constants.errorRegisterWithInvalidPIDLength));
    }
}
