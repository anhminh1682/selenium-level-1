package com.railway.pages;

import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.MailSlurp;
import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.*;

public class RegisterPage extends BasePage {
    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("password");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By pidTextBox = By.id("pid");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By errorRegisterMessage = By.xpath("//p[@class='message error']");
    private final By errorMessageOfPasswordField = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By errorMessageOfPIDField = By.xpath("//label[@class='validation-error' and @for='pid']");

    public void registerUserAccount(AccountEnum accountEnum) {
        LogUtils.info("Enter email: " + accountEnum.getUsername());
        sendKeyElement(emailTextBox, accountEnum.getUsername());

        LogUtils.info("Enter password: " + accountEnum.getPassword());
        sendKeyElement(passwordTextBox, accountEnum.getPassword());

        LogUtils.info("Enter confirm password: " + accountEnum.getConfirmPassword());
        sendKeyElement(confirmPasswordTextBox, accountEnum.getConfirmPassword());

        LogUtils.info("Enter PID: " + accountEnum.getPID());
        sendKeyElement(pidTextBox, accountEnum.getPID());

        LogUtils.info("Click button: " + getValueOfButton(registerButton));
        clickOnElement(registerButton);
    }

    public void registerUserAccount(Account account) {
        LogUtils.info("Enter email: " + account.getUsername());
        sendKeyElement(emailTextBox, account.getUsername());

        LogUtils.info("Enter password: " + account.getPassword());
        sendKeyElement(passwordTextBox, account.getPassword());

        LogUtils.info("Enter confirm password: " + account.getConfirmPassword());
        sendKeyElement(confirmPasswordTextBox, account.getConfirmPassword());

        LogUtils.info("Enter PID: " + account.getPid());
        sendKeyElement(pidTextBox, account.getPid());

        LogUtils.info("Click button: " + getValueOfButton(registerButton));
        clickOnElement(registerButton);
    }

    public void registerWithValidInfo() {
        registerUserAccount(AccountEnum.VALID_ACCOUNT_REGISTER);
    }

    public String getErrorRegisterMessage() {
        return getElementText(errorRegisterMessage);
    }

    public String getRegisterFailedWithInvalidPasswordMessage() {
        return getElementText(errorMessageOfPasswordField);
    }

    public String getRegisterFailedWithInvalidPIDMessage() {
        return getElementText(errorMessageOfPIDField);
    }
}
