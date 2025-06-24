package com.tests.forgotpassword;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import com.example.pages.BasePage;
import com.example.pages.ForgotPasswordPage;
import com.example.pages.LoginPage;
import com.example.pages.ResetPasswordPage;
import com.example.utilities.MailBoxManager;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC12 extends TestBase {
    @Test
    public void testcase1() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        basePage.clickToTab("Login");
        loginPage.goToForgotPasswordLink();

        // Forgot password
        forgotPasswordPage.sendInstructions(Constants.validUserName);
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertTrue(resetPasswordPage.checkResetPasswordPageDisplayed());

        resetPasswordPage.resetPassword(Constants.validPassword, Constants.validPassword, "");
        Assert.assertTrue(resetPasswordPage.checkResetTokenInvalid());
    }
}
