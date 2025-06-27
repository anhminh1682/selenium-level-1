package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06 extends TestBase {
    @Test
    public void additionalPagesDisplayOnceUserLoggedIn() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. Login with valid account");
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "Check my ticket tab is displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Check change password tab is displayed");
        Assert.assertTrue(homePage.isLogOutTabDisplayed(), "Check log out tab is displayed");

        homePage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);
        Assert.assertEquals(myTicketPage.getPageTitle(), Constants.PageTitles.MY_TICKET_PAGE_TITLE);

        myTicketPage.clickOnTab(Constants.TabMenu.CHANGE_PASSWORD_TAB);
        Assert.assertEquals(changePasswordPage.getPageTitle(), Constants.PageTitles.CHANGE_PASSWORD_PAGE_TITLE);
    }
}
