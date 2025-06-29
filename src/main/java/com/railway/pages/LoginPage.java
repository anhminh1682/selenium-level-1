package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.Account;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By userNameTextBox = By.id("username");
    private final By passwordTextBox = By.id("password");
    private final By loginButton = By.xpath("//input[@type='submit'][@title='Login']");
    private final By errorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.linkText("Forgot Password page");

    public void login(Account account) {
        LogUtils.info("Enter username: " + account.getUsername());
        webElement(userNameTextBox).sendKeys(account.getUsername());
        LogUtils.info("Enter password: " + account.getPassword());
        webElement(passwordTextBox).sendKeys(account.getPassword());

        Helpers.scrollToElement(webElement(loginButton));

        LogUtils.info("click button: " + webElement(loginButton).getText());
        webElement(loginButton).click();
    }

    public void loginSuccess() {
        login(Account.VALID_ACCOUNT_LOGIN);
    }

    public void loginMultipleTimes(int num, Account account) {
        for (int i = 0; i < num; i++) {
            this.login(account);
        }
    }

    public String getErrorMessage() {
        LogUtils.info("Error message" + webElement(errorMessage).getText());
        return webElement(errorMessage).getText();
    }

    public String getLoginPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public void goToForgotPasswordLink() {
        webElement(forgotPasswordLink).click();
    }
}
