package com.tests.forgotpassword;

import com.railway.constant.Constants;
import com.railway.pages.ForgotPasswordPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.pages.ResetPasswordPage;
import com.railway.utilities.MailBoxManager;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC13 extends TestBase {
    @Test
    public void errorsDisplayIfPasswordAndConfirmPasswordDontMatchWhenResettingPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.goToForgotPasswordLink();

        // Forgot password
        forgotPasswordPage.sendInstructions(Constants.Account.VALID_USERNAME);
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        resetPasswordPage.resetPassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD + "1", true);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_COULD_NOT_RESET_PASSWORD);
        Assert.assertEquals(resetPasswordPage.getErrorMessageConfirmPasswordNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_CONFIRM_PASSWORD_NEXT_TO_FIELD);
    }
}
