package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.*;
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

        homePage.clickOnTab("Login");

        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "Check my ticket tab is displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Check change password tab is displayed");
        Assert.assertTrue(homePage.isLogOutTabDisplayed(), "Check log out tab is displayed");

        homePage.clickOnTab("My ticket");
        Assert.assertEquals(myTicketPage.getMyTicketPageTitle(), Constants.PageTitles.MY_TICKET_PAGE_TITLE);

        myTicketPage.clickOnTab("Change password");
        Assert.assertEquals(changePasswordPage.getPageTitle(), Constants.PageTitles.CHANGE_PASSWORD_PAGE_TITLE);
    }
}
