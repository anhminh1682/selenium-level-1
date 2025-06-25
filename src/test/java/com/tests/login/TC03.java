package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03 extends TestBase {
    @Test
    public void UserCannotLogIntoRailwayWithInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.INVALID_PASSWORD);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD);
    }
}
