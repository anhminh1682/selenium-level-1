package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.railway.utilities.DriverUtils.*;

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
        LogUtils.info("Enter new password: " + accountEnum.getNewPassword());
        sendKeyElement(newPasswordTextBox, accountEnum.getPassword());
        LogUtils.info("Enter confirm password: " + accountEnum.getConfirmPassword());
        sendKeyElement(confirmPasswordTextBox, accountEnum.getConfirmPassword());

        if(!accountEnum.getToken()) {
            LogUtils.info("Remove token");
            sendKeyElement(resetTokenTextBox, Keys.CONTROL + "a");
            sendKeyElement(resetTokenTextBox, Keys.DELETE);
        }

        LogUtils.info("Click button: " + getValueOfButton(submitButton));
        clickOnElement(submitButton);
    }

    public void resetPassword(Account account) {
        LogUtils.info("Enter new password: " + account.getNewPassword());
        sendKeyElement(newPasswordTextBox, account.getNewPassword());
        LogUtils.info("Enter confirm password: " + account.getConfirmPassword());
        sendKeyElement(confirmPasswordTextBox, account.getConfirmPassword());

        if(!account.isToken()) {
            LogUtils.info("Remove token");
            sendKeyElement(resetTokenTextBox, Keys.CONTROL + "a");
            sendKeyElement(resetTokenTextBox, Keys.DELETE);
        }

        LogUtils.info("Click button: " + getValueOfButton(submitButton));
        clickOnElement(submitButton);
    }

    public boolean isErrorMessageAboveDisplayed() {
        return !DriverManager.getDriver().findElements(errorMessage).isEmpty();
    }

    public String getErrorMessageAbove() {
        return getElementText(errorMessage);
    }

    public String getErrorMessageInvalidResetTokenNextToField() {
        return getElementText(errorResetTokenMessage);
    }

    public String getErrorMessageConfirmPasswordNextToField() {
        return getElementText(errorConfirmPasswordMessage);
    }
}
