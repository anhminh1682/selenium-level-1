package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.ChangePasswordPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.LogUtils;
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
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Login with valid account");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        // Change Password
        LogUtils.info("3. Click on 'Change Password' tab");
        LogUtils.info("4. Enter valid value into all fields.");
        LogUtils.info("5. Click on 'Change Password' button");
        homePage.clickOnTab(Constants.TabMenu.CHANGE_PASSWORD_TAB);
        changePasswordPage.changePassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD);

        Assert.assertTrue(changePasswordPage.isChangePasswordTabDisplayed());
        Assert.assertEquals(changePasswordPage.getChangePasswordSuccessMessage(), Constants.ChangePasswordMessage.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }
};
