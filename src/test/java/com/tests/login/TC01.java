package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01 extends TestBase {
    @Test
    public void userCanLogIntoRailwayWithValidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. Enter valid Email and Password:");
        LogUtils.info("4. Click on 'Login' button");
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        Assert.assertEquals(homePage.getPageTitle(), Constants.PageTitles.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getWelComeText(), "Welcome " + Constants.Account.VALID_USERNAME);
    }
}
