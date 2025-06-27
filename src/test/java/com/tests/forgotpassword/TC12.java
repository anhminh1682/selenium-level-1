package com.tests.forgotpassword;

import com.mailslurp.clients.ApiException;
import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utilities.LogUtils;
import com.railway.utilities.MailBoxManager;
import com.railway.utilities.MailSlurp;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class TC12 extends TestBase {
    @Test
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseUI() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Login page");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("2. Click on 'Forgot Password page' link");
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.info("3. Enter the email address of the created account in Pre-condition");
        LogUtils.info("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendInstructions(Constants.Account.VALID_USERNAME);

        LogUtils.info("5. Open mailbox and click on reset password link");
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        LogUtils.info("6. Enter new passwords and remove the Password Reset Token");
        LogUtils.info("7. Click 'Reset Password' button");
        resetPasswordPage.resetPassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, false);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE);
        Assert.assertEquals(resetPasswordPage.getErrorMessageInvalidResetTokenNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD);
    }

    @Test
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseMailAPI() throws ApiException {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        // Created Email
        MailSlurp.createEmailInbox();
        String emailAddress = MailSlurp.getEmailAddressCreated();

        LogUtils.info("Pre-condition: Create and activate a new account");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        registerPage.registerUserAccount(
                emailAddress,
                Constants.Account.VALID_PASSWORD,
                Constants.Account.VALID_PASSWORD,
                Constants.Account.VALID_PID
        );

        DriverManager.getDriver().get(Objects.requireNonNull(MailSlurp.getResetPasswordLinkInEmail(MailSlurp.receiveEmail())));

        LogUtils.info("1. Navigate to QA Railway Login page");
        LogUtils.info("2. Click on 'Forgot Password page' link");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.info("3. Enter the email address of the created account in Pre-condition");
        LogUtils.info("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendInstructions(emailAddress);

        LogUtils.info("5. Open mailbox and click on reset password link");
        DriverManager.getDriver().get(Objects.requireNonNull(MailSlurp.getResetPasswordLinkInEmail(MailSlurp.receiveEmail())));

        // Reset password
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        LogUtils.info("6. Enter new passwords and remove the Password Reset Token");
        LogUtils.info("7. Click 'Reset Password' button");
        resetPasswordPage.resetPassword(Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, false);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE);
        Assert.assertEquals(resetPasswordPage.getErrorMessageInvalidResetTokenNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD);
    }
}
