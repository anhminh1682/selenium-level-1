package com.railway.pages;

import com.railway.utilities.Account;
import com.railway.utilities.DriverUtils;
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
        webElement(emailTextBox).clear();
        webElement(emailTextBox).sendKeys(accountEnum.getUsername());

        LogUtils.info("Enter password: " + accountEnum.getPassword());
        webElement(passwordTextBox).clear();
        webElement(passwordTextBox).sendKeys(accountEnum.getPassword());

        LogUtils.info("Enter confirm password: " + accountEnum.getConfirmPassword());
        webElement(confirmPasswordTextBox).sendKeys(accountEnum.getConfirmPassword());

        LogUtils.info("Enter PID: " + accountEnum.getPID());
        webElement(pidTextBox).clear();
        webElement(pidTextBox).sendKeys(accountEnum.getPID());

        LogUtils.info("Click button: " + webElement(registerButton).getText());
        clickOnElement(registerButton);
    }

    public void registerUserAccount(Account account) {
        LogUtils.info("Enter email: " + account.getUsername());
        webElement(emailTextBox).clear();
        webElement(emailTextBox).sendKeys(account.getUsername());

        LogUtils.info("Enter password: " + account.getPassword());
        webElement(passwordTextBox).clear();
        webElement(passwordTextBox).sendKeys(account.getPassword());

        LogUtils.info("Enter confirm password: " + account.getConfirmPassword());
        webElement(confirmPasswordTextBox).sendKeys(account.getConfirmPassword());

        LogUtils.info("Enter PID: " + account.getPid());
        webElement(pidTextBox).clear();
        webElement(pidTextBox).sendKeys(account.getPid());

        LogUtils.info("Click button: " + webElement(registerButton).getText());
        clickOnElement(registerButton);
    }

    public void registerWithValidInfo() {
        registerUserAccount(AccountEnum.VALID_ACCOUNT_REGISTER);
    }

    public String registerWithMailSlurp() {
        try {
            MailSlurp.createEmailInbox();
            AccountEnum accountEnum = AccountEnum.VALID_ACCOUNT_REGISTER_MAIL_SLURP;
            registerUserAccount(accountEnum);
            return accountEnum.getUsername();
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
