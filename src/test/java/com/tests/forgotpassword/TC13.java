package com.tests.forgotpassword;

import com.railway.constant.Constants;
import com.railway.pages.ForgotPasswordPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.pages.ResetPasswordPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.MailBoxManager;
import com.railway.utilities.enums.AccountEnum;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TC13 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayIfPasswordAndConfirmPasswordDontMatchWhenResettingPassword(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        LogUtils.startStep("Pre-condition: Create and activate a new account");
        LogUtils.startStep("1. Navigate to QA Railway Login page");
        LogUtils.startStep("2. Click on 'Forgot Password page' link");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
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

        LogUtils.startStep("5. Reset password different values for password fields");
        Account account = new Account(
                data.get(Constants.DataKeys.NEW_PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                true
        );
        resetPasswordPage.resetPassword(account);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_COULD_NOT_RESET_PASSWORD);
        Assert.assertEquals(resetPasswordPage.getErrorMessageConfirmPasswordNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_CONFIRM_PASSWORD_NEXT_TO_FIELD);
    }
}
