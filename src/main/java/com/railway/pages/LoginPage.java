package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By userNameTextBox = By.id("username");
    private final By passwordTextBox = By.id("password");
    private final By loginButton = By.xpath("//input[@type='submit'][@title='Login']");
    private final By errorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.linkText("Forgot Password page");

    public void login(String username, String password) {
        webElement(userNameTextBox).sendKeys(username);
        webElement(passwordTextBox).sendKeys(password);

        Helpers.scrollToElement(webElement(loginButton));

        webElement(loginButton).click();
    }

    public void loginMultipleTimes(int num, String username, String password) {
        for (int i = 0; i < num; i++) {
            this.login(username, password);
        }
    }

    public String getErrorMessage() {
        return webElement(errorMessage).getText();
    }

    public String getLoginPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public void goToForgotPasswordLink() {
        webElement(forgotPasswordLink).click();
    }
}
