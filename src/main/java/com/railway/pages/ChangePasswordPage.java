package com.railway.pages;

import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.*;

public class ChangePasswordPage extends BasePage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By changePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    public void changePassword(AccountEnum accountEnum) {
        LogUtils.info("Password" + accountEnum.getPassword());
        getWebElement(currentPasswordTextBox).sendKeys(accountEnum.getPassword());

        LogUtils.info("New Password" + accountEnum.getNewPassword());
        getWebElement(newPasswordTextBox).sendKeys(accountEnum.getNewPassword());
        LogUtils.info("Confirm Password" + accountEnum.getConfirmPassword());
        getWebElement(confirmPasswordTextBox).sendKeys(accountEnum.getConfirmPassword());

        LogUtils.info("Click button " + getWebElement(changePasswordButton).getText());
        clickOnElement(changePasswordButton);
    }

    public void changePassword(Account account) {
        LogUtils.info("Password" + account.getPassword());
        getWebElement(currentPasswordTextBox).sendKeys(account.getPassword());

        LogUtils.info("New Password" + account.getNewPassword());
        getWebElement(newPasswordTextBox).sendKeys(account.getNewPassword());
        LogUtils.info("Confirm Password" + account.getConfirmPassword());
        getWebElement(confirmPasswordTextBox).sendKeys(account.getConfirmPassword());

        LogUtils.info("Click button " + getWebElement(changePasswordButton).getText());
        clickOnElement(changePasswordButton);
    }

    public String getChangePasswordSuccessMessage() {
        return getElementText(changePasswordSuccessMessage);
    }
}
