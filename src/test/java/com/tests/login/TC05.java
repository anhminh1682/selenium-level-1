package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05 extends TestBase {
    @Test
    public void systemShowsMessageWhenUserEntersWrongPasswordSeveralTimes() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        int n = 4;

        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. Enter valid information into 'Username' textbox except 'Password' textbox.");
        LogUtils.info("4. Click on 'Login' button");
        LogUtils.info("5. Repeat step 3 three more times.");
        loginPage.loginMultipleTimes(n, Constants.Account.VALID_USERNAME, Constants.Account.INACTIVE_USERNAME_LOGIN);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH);
    }
}
