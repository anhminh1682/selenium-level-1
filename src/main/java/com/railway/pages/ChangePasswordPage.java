package com.railway.pages;

import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import org.openqa.selenium.By;

public class ChangePasswordPage extends BasePage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By changePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    public void changePassword(String password, String newPassword, String confirmPassword) {
        LogUtils.info("Password" + password);
        webElement(currentPasswordTextBox).sendKeys(password);

        LogUtils.info("New Password" + newPassword);
        webElement(newPasswordTextBox).sendKeys(newPassword);
        LogUtils.info("Confirm Password" + confirmPassword);
        webElement(confirmPasswordTextBox).sendKeys(confirmPassword);

        LogUtils.info("Click button " + webElement(changePasswordButton).getText());
        Helpers.scrollToElement(webElement(changePasswordButton));
        webElement(changePasswordButton).click();
    }

    public String getChangePasswordSuccessMessage() {
        return getElementText(webElement(changePasswordSuccessMessage));
    }
}
