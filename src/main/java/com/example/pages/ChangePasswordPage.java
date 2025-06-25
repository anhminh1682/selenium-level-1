package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import com.example.utilities.Helpers;
import org.openqa.selenium.By;

import java.util.Objects;

public class ChangePasswordPage extends BasePage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By changePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    public void changePassword(String password, String newPassword, String confirmPassword) {
        webElement(currentPasswordTextBox).sendKeys(password);
        webElement(newPasswordTextBox).sendKeys(newPassword);
        webElement(confirmPasswordTextBox).sendKeys(confirmPassword);

        Helpers.scrollToElement(webElement(changePasswordButton));

        webElement(changePasswordButton).click();
    }

    public String getChangePasswordSuccessMessage() {
        return getElementText(webElement(changePasswordSuccessMessage));
    }
}
