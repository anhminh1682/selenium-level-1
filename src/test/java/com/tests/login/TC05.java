package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
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
        loginPage.loginMultipleTimes(n, Constants.Account.VALID_USERNAME, Constants.Account.INVALID_PASSWORD);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH);
    }
}
