package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04 extends TestBase {
    @Test
    public void loginPageDisplaysWhenUnLoggedUserClicksOnBookTicketTab() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Book ticket' tab");
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.PageTitles.LOGIN_PAGE_TITLE);
    }
}
