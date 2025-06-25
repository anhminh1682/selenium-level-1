package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.ChangePasswordPage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC09 extends TestBase {
    @Test
    public void userCanChangePassword() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        // Login
        homePage.clickOnTab("Login");
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        // Change Password
        homePage.clickOnTab("Change password");
        changePasswordPage.changePassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD);

        Assert.assertEquals(changePasswordPage.getChangePasswordSuccessMessage(), Constants.ChangePasswordMessage.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }
};
