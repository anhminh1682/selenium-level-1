package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class RegisterPage {
    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("password");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By pidTextBox = By.id("pid");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By registerValidInfoHeading = By.xpath("//h1[text()='" + Constants.registerValidInfoHeading + "']");
    private final By errorRegisterMessage = By.xpath("//p[@class='message error']");
    private final By errorMessageOfPasswordField = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By errorMessageOfPIDField = By.xpath("//label[@class='validation-error' and @for='pid']");

    private WebElement getEmailTextBox() {
        return DriverManager.getDriver().findElement(emailTextBox);
    }

    private WebElement getPasswordTextBox() {
        return DriverManager.getDriver().findElement(passwordTextBox);
    }

    private WebElement getConfirmPasswordTextBox() {
        return DriverManager.getDriver().findElement(confirmPasswordTextBox);
    }

    private WebElement getPIDTextBox() {
        return DriverManager.getDriver().findElement(pidTextBox);
    }

    private WebElement getRegisterButton() {
        return DriverManager.getDriver().findElement(registerButton);
    }

    private WebElement getRegisterValidInfoHeading() {
        return DriverManager.getDriver().findElement(registerValidInfoHeading);
    }

    private WebElement getErrorRegisterMessage() {
        return DriverManager.getDriver().findElement(errorRegisterMessage);
    }

    private WebElement getErrorMessageOfPasswordField() {
        return DriverManager.getDriver().findElement(errorMessageOfPasswordField);
    }

    private WebElement getErrorMessageOfPIDField() {
        return DriverManager.getDriver().findElement(errorMessageOfPIDField);
    }

    public void register(String email, String password, String confirmPassword, String pid) {
        getEmailTextBox().sendKeys(email);
        getPasswordTextBox().sendKeys(password);
        getConfirmPasswordTextBox().sendKeys(confirmPassword);
        getPIDTextBox().sendKeys(pid);

        getRegisterButton().click();
    }

    public boolean checkRegisterSuccessWithValidInfor() {
        return getRegisterValidInfoHeading().isDisplayed();
    }

    public boolean checkRegisterFailedWithInvalidInfo() {
        return Objects.equals(getErrorRegisterMessage().getText().trim(), Constants.errorRegisterWithInvalidInfo);
    }

    public boolean checkRegisterFailedWithInvalidPassword(String errorMessage) {
        return Objects.equals(getErrorMessageOfPasswordField().getText().trim(), errorMessage);
    }

    public boolean checkRegisterFailedWithInvalidPID(String errorMessage) {
        return Objects.equals(getErrorMessageOfPIDField().getText().trim(), errorMessage);
    }
}
