package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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

            if(Objects.equals(DriverManager.getDriver().getTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE)) break;
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

    public boolean isErrorMessageIncorrectResetTokenAboveDsiplayed() {
        return !DriverManager.getDriver().findElements(errorMessage).isEmpty();
    }

    public String getErrorMessageIncorrectResetTokenAbove() {
        return getElementText(webElement(errorMessage));
    }

    public String getErrorMessageInvalidResetTokenNextToField() {
        return getElementText(webElement(errorResetTokenMessage));
    }
}
