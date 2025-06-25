package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01 extends TestBase {
    @Test
    public void userCanLogIntoRailwayWithValidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        Assert.assertEquals(homePage.getWelComeText(), "Welcome " + Constants.Account.VALID_USERNAME);
    }
}
