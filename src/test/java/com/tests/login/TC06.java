package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.BasePage;
import com.example.pages.ChangePasswordPage;
import com.example.pages.LoginPage;
import com.example.pages.MyTicketPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();

        basePage.goToLoginPage();

        loginPage.login(Constants.validUserName, Constants.validPassword);

        Assert.assertTrue(basePage.checkMyTicketTabDisplayed());
        Assert.assertTrue(basePage.checkChangePasswordTabDisplayed());
        Assert.assertTrue(basePage.checkLogOutTabDisplayed());

        basePage.goToMyTicketTab();
        Assert.assertTrue(new MyTicketPage().checkMyTicketPageDisplayed());

        basePage.goToChangePassword();
        Assert.assertTrue(new ChangePasswordPage().checkChangePasswordPageDisplayed());
    }
}
