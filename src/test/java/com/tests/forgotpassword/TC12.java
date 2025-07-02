package com.tests.forgotpassword;

import com.mailslurp.clients.ApiException;
import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.pages.*;
import com.railway.utilities.*;
import com.railway.utilities.enums.AccountEnum;
import com.tests.base.TestBase;
import com.tests.ultilities.MailHelpers.MailHelpers;
import com.tests.ultilities.MailHelpers.mailDataFromJSON;
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

    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseMailSlurpMethod(Map<String, Object> data) throws ApiException {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        MailSlurp mailSlurp = new MailSlurp();
        mailSlurp.createEmailInbox();

        // Created Email
        LogUtils.startStep("Pre-condition: Create and activate a new account");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        Account accountRegister = new Account(
                mailSlurp.getEmailAddressCreated(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPID()
        );
        String emailSlurp = mailSlurp.getEmailAddressCreated();
        registerPage.registerUserAccount(accountRegister);
        DriverManager.getDriver().get(Objects.requireNonNull(Helpers.getLinkInEmail(mailSlurp.receiveEmail(Constants.MailSlurp.SUBJECT_EMAIL_CONFIRM))));

        LogUtils.startStep("1. Navigate to QA Railway Login page");
        LogUtils.startStep("2. Click on 'Forgot Password page' link");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.startStep("3. Enter the email address of the created account in Pre-condition");
        forgotPasswordPage.sendInstructions(emailSlurp);

        LogUtils.startStep("4. Open mailbox and click on reset password link");
        DriverManager.getDriver().get(Objects.requireNonNull(Helpers.getLinkInEmail(mailSlurp.receiveEmail(Constants.MailSlurp.SUBJECT_EMAIL_RESET_PASSWORD))));

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

    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayWhenPasswordResetTokenIsBlankUseMailSlurpAPI(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
        RegisterPage registerPage = new RegisterPage();

        Map<String, String> emailDatas = mailDataFromJSON.mailData();

        String emailAddress = emailDatas.get("emailAddress");
        String inboxId = emailDatas.get("inboxId");

        boolean isEmailExpired = MailHelpers.callToCheckInboxIdExpired(inboxId);

        if(isEmailExpired) {
            String[] emailInfos = MailHelpers.callToCreateInbox();
            emailAddress = emailInfos[0];
            inboxId = emailInfos[1];
        }
        mailDataFromJSON.writeDataToJsonFile(inboxId, emailAddress);

        String [] emailSplit = emailAddress.split("@");
        emailAddress = emailSplit[0] + "+" + Helpers.randomBase62(4) + "@" + emailSplit[1];

        LogUtils.startStep("Pre-condition: Create and activate a new account");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        Account accountRegister = new Account(
                emailAddress,
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPID()
        );

        registerPage.registerUserAccount(accountRegister);

        String linkRegister = MailHelpers.getLinkInEmailByAPI(Constants.MailSlurp.EMAIL_CONFIRM_SUBJECT + " " + emailAddress);

        Assert.assertNotNull(linkRegister);
        DriverManager.getDriver().get(linkRegister);

        LogUtils.startStep("1. Navigate to QA Railway Login page");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.startStep("2. Click on 'Forgot Password page' link");
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.startStep("3. Enter the email address of the created account in Pre-condition");
        forgotPasswordPage.sendInstructions(emailAddress);

        LogUtils.startStep("4. Open mailbox and click on reset password link");

        String linkResetPassword = MailHelpers.getLinkInEmailByAPI(Constants.MailSlurp.EMAIL_RESET_PASSWORD_SUBJECT + " " + emailAddress);
        DriverManager.getDriver().get(linkResetPassword);

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
}
