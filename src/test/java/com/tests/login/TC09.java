package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.ChangePasswordPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TC09 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanChangePassword(Map<String, Object> data) {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        // Login
        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Login with valid account");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.loginSuccess();

        // Change Password
        LogUtils.startStep("3. Click on 'Change Password' tab");
        homePage.clickOnTab(Constants.TabMenu.CHANGE_PASSWORD_TAB);

        LogUtils.startStep("4. Change password with valid value into all fields.");
        Account account = new Account(
            data.get(Constants.DataKeys.PASSWORD).toString(),
            data.get(Constants.DataKeys.NEW_PASSWORD).toString(),
            data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString()
        );
        changePasswordPage.changePassword(account);

        Assert.assertTrue(changePasswordPage.isChangePasswordTabDisplayed());
        Assert.assertEquals(changePasswordPage.getChangePasswordSuccessMessage(), Constants.ChangePasswordMessage.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }
};
