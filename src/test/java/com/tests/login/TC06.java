package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TC06 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void additionalPagesDisplayOnceUserLoggedIn(Map<String, Object> data) {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.startStep("3. Login with valid account");
        Account account = new Account(data.get(Constants.DataKeys.USERNAME).toString(), data.get(Constants.DataKeys.PASSWORD).toString());
        loginPage.login(account);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "Check my ticket tab is displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Check change password tab is displayed");
        Assert.assertTrue(homePage.isLogOutTabDisplayed(), "Check log out tab is displayed");

        homePage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);
        Assert.assertEquals(myTicketPage.getPageTitle(), Constants.PageTitles.MY_TICKET_PAGE_TITLE);

        myTicketPage.clickOnTab(Constants.TabMenu.CHANGE_PASSWORD_TAB);
        Assert.assertEquals(changePasswordPage.getPageTitle(), Constants.PageTitles.CHANGE_PASSWORD_PAGE_TITLE);
    }
}
