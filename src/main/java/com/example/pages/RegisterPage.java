package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import com.example.utilities.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class RegisterPage extends BasePage {
    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("password");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By pidTextBox = By.id("pid");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By registerValidInfoHeading = By.xpath("//h1[@align='center']");
    private final By errorRegisterMessage = By.xpath("//p[@class='message error']");
    private final By errorMessageOfPasswordField = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By errorMessageOfPIDField = By.xpath("//label[@class='validation-error' and @for='pid']");

    public void registerUserAccount(String email, String password, String confirmPassword, String pid) {
        webElement(emailTextBox).clear();
        webElement(emailTextBox).sendKeys(email);

        webElement(passwordTextBox).clear();
        webElement(passwordTextBox).sendKeys(password);

        webElement(confirmPasswordTextBox).sendKeys(confirmPassword);

        webElement(pidTextBox).clear();
        webElement(pidTextBox).sendKeys(pid);

        Helpers.scrollToElement(webElement(registerButton));
        webElement(registerButton).click();
    }

    public String getRegisterValidInforHeading() {
        return getElementText(webElement(registerValidInfoHeading));
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
