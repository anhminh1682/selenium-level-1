package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.Account;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08 extends TestBase {
    @Test
    public void userCannotLoginWithAnAccountHasNotBeenActivated() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Login
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. Enter username and password of account hasn't been activated.");
        LogUtils.info("4. Click on 'Login' button");
        loginPage.login(Account.INACTIVE_USERNAME_LOGIN);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_INVALID_FIELDS);
    }
}
