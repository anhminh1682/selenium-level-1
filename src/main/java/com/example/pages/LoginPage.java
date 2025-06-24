package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class LoginPage {
    private final By userNameTextBox = By.id("username");
    private final By passwordTextBox = By.id("password");
    private final By loginButton = By.xpath("//input[@type='submit'][@title='Login']");
    private final By errorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.linkText("Forgot Password page");

    private WebElement getUserNameTextBox() {
        return DriverManager.getDriver().findElement(userNameTextBox);
    }

    private WebElement getPasswordTextBox() {
        return DriverManager.getDriver().findElement(passwordTextBox);
    }

    private WebElement getLoginButton() {
        return DriverManager.getDriver().findElement(loginButton);
    }

    private WebElement getErrorMessage() {
        return DriverManager.getDriver().findElement(errorMessage);
    }

    private WebElement getForgotPasswordLink() {
        return DriverManager.getDriver().findElement(forgotPasswordLink);
    }

    public void login(String username, String password) {
        getUserNameTextBox().sendKeys(username);
        getPasswordTextBox().sendKeys(password);

        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, getLoginButton().getRect().y)
                .perform();

        getLoginButton().click();
    }

    public boolean checkLoginFailed(String errorMessage) {
        return Objects.equals(getErrorMessage().getText(), errorMessage);
    }

    public boolean checkLoginPageDisplayed() {
        return Objects.equals(DriverManager.getDriver().getTitle(), Constants.loginPageTitle);
    }

    public void goToForgotPasswordLink() {
        getForgotPasswordLink().click();
    }
}
