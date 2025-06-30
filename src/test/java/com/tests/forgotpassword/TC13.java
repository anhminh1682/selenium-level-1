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

public class TC13 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void errorsDisplayIfPasswordAndConfirmPasswordDontMatchWhenResettingPassword(String username, String newPassword, String confirmPassword) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Login page");
        LogUtils.info("2. Click on 'Forgot Password page' link");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.goToForgotPasswordLink();

        // Forgot password
        LogUtils.info("3. Enter the email address of the created account in Pre-condition");
        LogUtils.info("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendInstructions(username);

        LogUtils.info("5. Open mailbox and click on reset password link");
        forgotPasswordPage.goToMailBox();

        // Mailbox
        MailBoxManager.clickResetPasswordLink();

        // Reset password
        resetPasswordPage.switchToResetPasswordTab();
        Assert.assertEquals(resetPasswordPage.getPageTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE);

        LogUtils.info("6. Enter different values for password fields");
        LogUtils.info("7. Click 'Reset Password' button");
        Account account = new Account(newPassword, confirmPassword, true);
        resetPasswordPage.resetPassword(account);

        Assert.assertTrue(resetPasswordPage.isErrorMessageAboveDisplayed(), "Error message element does not exist");
        Assert.assertEquals(resetPasswordPage.getErrorMessageAbove(), Constants.ResetPasswordMessage.ERROR_MESSAGE_COULD_NOT_RESET_PASSWORD);
        Assert.assertEquals(resetPasswordPage.getErrorMessageConfirmPasswordNextToField(), Constants.ResetPasswordMessage.ERROR_MESSAGE_CONFIRM_PASSWORD_NEXT_TO_FIELD);
    }
}
