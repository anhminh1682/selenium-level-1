package com.railway.pages;

import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import com.railway.utilities.MailSlurp;
import com.railway.utilities.enums.Account;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {
    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("password");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By pidTextBox = By.id("pid");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By errorRegisterMessage = By.xpath("//p[@class='message error']");
    private final By errorMessageOfPasswordField = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By errorMessageOfPIDField = By.xpath("//label[@class='validation-error' and @for='pid']");

    public void registerUserAccount(Account account) {
        LogUtils.info("Enter email: " + account.getUsername());
        webElement(emailTextBox).clear();
        webElement(emailTextBox).sendKeys(account.getUsername());

        LogUtils.info("Enter password: " + account.getPassword());
        webElement(passwordTextBox).clear();
        webElement(passwordTextBox).sendKeys(account.getPassword());

        LogUtils.info("Enter confirm password: " + account.getConfirmPassword());
        webElement(confirmPasswordTextBox).sendKeys(account.getConfirmPassword());

        LogUtils.info("Enter PID: " + account.getPID());
        webElement(pidTextBox).clear();
        webElement(pidTextBox).sendKeys(account.getPID());

        LogUtils.info("Click button: " + webElement(registerButton).getText());
        Helpers.scrollToElement(webElement(registerButton));
        webElement(registerButton).click();
    }

    public void registerWithValidInfo() {
        registerUserAccount(Account.VALID_ACCOUNT_REGISTER);
    }

    public String registerWithMailSlurp() {
        try {
            MailSlurp.createEmailInbox();
            Account account = Account.VALID_ACCOUNT_REGISTER_MAIL_SLURP;
            registerUserAccount(account);
            return account.getUsername();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getErrorRegisterMessage() {
        return getElementText(webElement(errorRegisterMessage));
    }

    public String getRegisterFailedWithInvalidPasswordMessage() {
        return getElementText(webElement(errorMessageOfPasswordField));
    }

    public String getRegisterFailedWithInvalidPIDMessage() {
        return getElementText(webElement(errorMessageOfPIDField));
    }
}
