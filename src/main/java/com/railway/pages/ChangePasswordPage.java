package com.railway.pages;

import com.railway.utilities.Account;
import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;

public class ChangePasswordPage extends BasePage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By changePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    public void changePassword(AccountEnum accountEnum) {
        LogUtils.info("Password" + accountEnum.getPassword());
        webElement(currentPasswordTextBox).sendKeys(accountEnum.getPassword());

        LogUtils.info("New Password" + accountEnum.getNewPassword());
        webElement(newPasswordTextBox).sendKeys(accountEnum.getNewPassword());
        LogUtils.info("Confirm Password" + accountEnum.getConfirmPassword());
        webElement(confirmPasswordTextBox).sendKeys(accountEnum.getConfirmPassword());

        LogUtils.info("Click button " + webElement(changePasswordButton).getText());
        Helpers.scrollToElement(webElement(changePasswordButton));
        webElement(changePasswordButton).click();
    }

    public void changePassword(Account account) {
        LogUtils.info("Password" + account.getPassword());
        webElement(currentPasswordTextBox).sendKeys(account.getPassword());

        LogUtils.info("New Password" + account.getNewPassword());
        webElement(newPasswordTextBox).sendKeys(account.getNewPassword());
        LogUtils.info("Confirm Password" + account.getConfirmPassword());
        webElement(confirmPasswordTextBox).sendKeys(account.getConfirmPassword());

        LogUtils.info("Click button " + webElement(changePasswordButton).getText());
        Helpers.scrollToElement(webElement(changePasswordButton));
        webElement(changePasswordButton).click();
    }

    public String getChangePasswordSuccessMessage() {
        return getElementText(webElement(changePasswordSuccessMessage));
    }
}
