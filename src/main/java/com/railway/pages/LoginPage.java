package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.*;

public class LoginPage extends BasePage {
    private final By userNameTextBox = By.id("username");
    private final By passwordTextBox = By.id("password");
    private final By loginButton = By.xpath("//input[@type='submit'][@title='Login']");
    private final By errorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.linkText("Forgot Password page");

    public void login(AccountEnum accountEnum) {
        LogUtils.info("Enter username: " + accountEnum.getUsername());
        sendKeyElement(userNameTextBox, accountEnum.getUsername());
        LogUtils.info("Enter password: " + accountEnum.getPassword());
        sendKeyElement(passwordTextBox, accountEnum.getPassword());

        LogUtils.info("click button: " + getWebElement(loginButton).getText());
        clickOnElement(loginButton);
    }

    public void login(Account account) {
        LogUtils.info("Enter username: " + account.getUsername());
        sendKeyElement(userNameTextBox, account.getUsername());
        LogUtils.info("Enter password: " + account.getPassword());
        sendKeyElement(passwordTextBox, account.getPassword());

        LogUtils.info("click button: " + getValueOfButton(loginButton));
        clickOnElement(loginButton);
    }

    public void loginSuccess() {
        login(AccountEnum.VALID_ACCOUNT_LOGIN);
    }

    public void loginMultipleTimes(int num, AccountEnum accountEnum) {
        for (int i = 0; i < num; i++) {
            this.login(accountEnum);
        }
    }

    public void loginMultipleTimes(int num, Account account) {
        for (int i = 0; i < num; i++) {
            this.login(account);
        }
    }

    public String getErrorMessage() {
        LogUtils.info("Error message: " + getWebElement(errorMessage).getText());
        return getWebElement(errorMessage).getText();
    }

    public String getLoginPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public void goToForgotPasswordLink() {
        clickOnElement(forgotPasswordLink);
    }
}
