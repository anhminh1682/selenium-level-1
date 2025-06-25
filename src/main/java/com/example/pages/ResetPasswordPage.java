package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import com.example.utilities.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ResetPasswordPage extends BasePage {
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By resetTokenTextBox = By.id("resetToken");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath("//p[@class='message error']");
    private final By errorResetTokenMessage = By.xpath("//label[@for='resetToken' and @class='validation-error']");

    public void switchToResetPasswordTab() {
        for(String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);

            if(Objects.equals(DriverManager.getDriver().getTitle(), Constants.resetPasswordPageTitle)) break;
        }
    }

    public void resetPassword(String newPassword, String confirmPassword, String resetToken) {
        webElement(newPasswordTextBox).sendKeys(newPassword);
        webElement(confirmPasswordTextBox).sendKeys(confirmPassword);

        if(resetToken.isEmpty()) {
            webElement(resetTokenTextBox).sendKeys(Keys.CONTROL + "a");
            webElement(resetTokenTextBox).sendKeys(Keys.DELETE);
        }
        else
            webElement(resetTokenTextBox).sendKeys(resetToken);

        Helpers.scrollToElement(webElement(submitButton));

        webElement(submitButton).click();
    }

    public String getErrorMessageIncorrectResetTokenAbove() {
        return getElementText(webElement(errorMessage));
    }

    public String getErrorMessageInvalidResetTokenNextToField() {
        return getElementText(webElement(errorResetTokenMessage));
    }
}
