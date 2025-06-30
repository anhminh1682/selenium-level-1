package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Account;
import com.railway.utilities.Helpers;
import com.railway.utilities.enums.AccountEnum;
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
    private final By errorConfirmPasswordMessage = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    public void switchToResetPasswordTab() {
        for(String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);

            if(Objects.equals(DriverManager.getDriver().getTitle(), Constants.PageTitles.RESET_PASSWORD_PAGE_TITLE)) break;
        }
    }

    public void resetPassword(AccountEnum accountEnum) {
        webElement(newPasswordTextBox).sendKeys(accountEnum.getPassword());
        webElement(confirmPasswordTextBox).sendKeys(accountEnum.getConfirmPassword());

        if(!accountEnum.getToken()) {
            webElement(resetTokenTextBox).sendKeys(Keys.CONTROL + "a");
            webElement(resetTokenTextBox).sendKeys(Keys.DELETE);
        }

        Helpers.scrollToElement(webElement(submitButton));

        webElement(submitButton).click();
    }

    public void resetPassword(Account account) {
        webElement(newPasswordTextBox).sendKeys(account.getNewPassword());
        webElement(confirmPasswordTextBox).sendKeys(account.getConfirmPassword());

        if(!account.isToken()) {
            webElement(resetTokenTextBox).sendKeys(Keys.CONTROL + "a");
            webElement(resetTokenTextBox).sendKeys(Keys.DELETE);
        }

        Helpers.scrollToElement(webElement(submitButton));

        webElement(submitButton).click();
    }

    public boolean isErrorMessageAboveDisplayed() {
        return !DriverManager.getDriver().findElements(errorMessage).isEmpty();
    }

    public String getErrorMessageAbove() {
        return getElementText(webElement(errorMessage));
    }

    public String getErrorMessageInvalidResetTokenNextToField() {
        return getElementText(webElement(errorResetTokenMessage));
    }

    public String getErrorMessageConfirmPasswordNextToField() {
        return getElementText(webElement(errorConfirmPasswordMessage));
    }
}
