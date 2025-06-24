package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ResetPasswordPage {
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By resetTokenTextBox = By.id("resetToken");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath("//p[@class='message error']");
    private final By errorResetTokenMessage = By.xpath("//label[@for='resetToken' and @class='validation-error']");

    private WebElement getNewPasswordTextBox() {
        return DriverManager.getDriver().findElement(newPasswordTextBox);
    }

    private WebElement getConfirmPasswordTextBox() {
        return DriverManager.getDriver().findElement(confirmPasswordTextBox);
    }

    private WebElement getResetTokenTextBox() {
        return DriverManager.getDriver().findElement(resetTokenTextBox);
    }

    private WebElement getErrorMessage() {
        return DriverManager.getDriver().findElement(errorMessage);
    }

    private WebElement getErrorResetTokenMessage() {
        return DriverManager.getDriver().findElement(errorResetTokenMessage);
    }

    private WebElement getSubmitButton() {
        return DriverManager.getDriver().findElement(submitButton);
    }

    public void switchToResetPasswordTab() {
        for(String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);

            if(Objects.equals(DriverManager.getDriver().getTitle(), Constants.resetPasswordPageTitle)) break;
        }
    }

    public boolean checkResetPasswordPageDisplayed() {
        return Objects.equals(DriverManager.getDriver().getTitle(), Constants.resetPasswordPageTitle);
    }

    public void resetPassword(String newPassword, String confirmPassword, String resetToken) {
        getNewPasswordTextBox().sendKeys(newPassword);
        getConfirmPasswordTextBox().sendKeys(confirmPassword);

        if(resetToken.isEmpty()) {
            getResetTokenTextBox().sendKeys(Keys.CONTROL + "a");
            getResetTokenTextBox().sendKeys(Keys.DELETE);
            getResetTokenTextBox().sendKeys("");
        }
        else
            getResetTokenTextBox().sendKeys(resetToken);

        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, getSubmitButton().getRect().y)
                .perform();

        getSubmitButton().click();
    }

    public boolean checkResetTokenInvalid() {
        return Objects.equals(getErrorMessage().getText(), Constants.errorMessageIncorrectResetTokenAbove)
                && Objects.equals(getErrorResetTokenMessage().getText(), Constants.errorMessageInvalidResetTokenNextToField);
    }
}
