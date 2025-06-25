package com.tests.forgotpassword;

import com.example.constant.Constants;
import com.example.pages.*;
import com.example.utilities.MailBoxManager;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC12 extends TestBase {
    @Test
    public void errorsDisplayWhenPasswordResetTokenIsBlank() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        homePage.clickOnTab("Login");
        loginPage.goToForgotPasswordLink();

        // Forgot password
        forgotPasswordPage.sendInstructions(Constants.Account.VALID_USERNAME);
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        resetPasswordPage.resetPassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, "");
        Assert.assertEquals(resetPasswordPage.getErrorMessageIncorrectResetTokenAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE);
        Assert.assertEquals(resetPasswordPage.getErrorMessageInvalidResetTokenNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD);
    }
}
