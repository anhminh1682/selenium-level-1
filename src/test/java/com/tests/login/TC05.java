package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05 extends TestBase {
    @Test
    public void systemShowsMessageWhenUserEntersWrongPasswordSeveralTimes() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        int n = 4;

        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.loginMultipleTimes(n, Constants.Account.VALID_USERNAME, Constants.Account.INACTIVE_USERNAME_LOGIN);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH);
    }
}
