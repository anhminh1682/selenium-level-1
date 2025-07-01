package com.tests.forgotpassword;

import com.mailslurp.clients.ApiException;
import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.MailBoxManager;
import com.railway.utilities.MailSlurp;
import com.railway.utilities.enums.AccountEnum;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

public class TC12 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseUI(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        LogUtils.startStep("Pre-condition: Create and activate a new account");
        LogUtils.startStep("1. Navigate to QA Railway Login page");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.startStep("2. Click on 'Forgot Password page' link");
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.startStep("3. Enter the email address of the created account in Pre-condition");
        forgotPasswordPage.sendInstructions(data.get(Constants.DataKeys.USERNAME).toString());

        LogUtils.startStep("4. Open mailbox and click on reset password link");
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        LogUtils.startStep("5. Reset password with new passwords and remove the Password Reset Token");
        Account account = new Account(
                data.get(Constants.DataKeys.NEW_PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                false
        );
        resetPasswordPage.resetPassword(account);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE);
        Assert.assertEquals(resetPasswordPage.getErrorMessageInvalidResetTokenNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD);
    }

//    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseMailAPI(Map<String, Object> data) throws ApiException {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        // Created Email
        LogUtils.startStep("Pre-condition: Create and activate a new account");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        String emailSlurp = registerPage.registerWithMailSlurp();

        DriverManager.getDriver().get(Objects.requireNonNull(MailSlurp.getLinkInEmail(MailSlurp.receiveEmail(Constants.MailSlurp.SUBJECT_EMAIL_CONFIRM))));

        LogUtils.startStep("1. Navigate to QA Railway Login page");
        LogUtils.startStep("2. Click on 'Forgot Password page' link");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.startStep("3. Enter the email address of the created account in Pre-condition");
        forgotPasswordPage.sendInstructions(emailSlurp);

        LogUtils.startStep("4. Open mailbox and click on reset password link");
        DriverManager.getDriver().get(Objects.requireNonNull(MailSlurp.getLinkInEmail(MailSlurp.receiveEmail(Constants.MailSlurp.SUBJECT_EMAIL_RESET_PASSWORD))));

        // Reset password
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        LogUtils.startStep("5. Reset password new passwords and remove the Password Reset Token");
        Account account = new Account(
                data.get(Constants.DataKeys.NEW_PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                false
        );
        resetPasswordPage.resetPassword(account);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE);
        Assert.assertEquals(resetPasswordPage.getErrorMessageInvalidResetTokenNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD);
    }
}
