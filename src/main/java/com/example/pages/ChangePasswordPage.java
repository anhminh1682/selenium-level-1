package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class ChangePasswordPage {
    private final By currentPasswordTextBox = By.id("currentPassword");
    private final By newPasswordTextBox = By.id("newPassword");
    private final By confirmPasswordTextBox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By changePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    private WebElement getCurrentPasswordTextBox() {
        return DriverManager.getDriver().findElement(currentPasswordTextBox);
    }

    private WebElement getNewPasswordTextBox() {
        return DriverManager.getDriver().findElement(newPasswordTextBox);
    }

    private WebElement getConfirmPasswordTextBox() {
        return DriverManager.getDriver().findElement(confirmPasswordTextBox);
    }

    private WebElement getChangePasswordButton() {
        return DriverManager.getDriver().findElement(changePasswordButton);
    }

    private WebElement getChangePasswordSuccessMessage() {
        return DriverManager.getDriver().findElement(changePasswordSuccessMessage);
    }

    public void changePassword(String password, String newPassword, String confirmPassword) {
        getCurrentPasswordTextBox().sendKeys(password);
        getNewPasswordTextBox().sendKeys(newPassword);
        getConfirmPasswordTextBox().sendKeys(confirmPassword);

        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, getChangePasswordButton().getRect().y)
                .perform();

        getChangePasswordButton().click();
    }

    public boolean checkChangePasswordPageDisplayed() {
        return Objects.equals(DriverManager.getDriver().getTitle(), Constants.changePasswordPageTitle);
    }

    public boolean checkChangePasswordSuccessful() {
        return Objects.equals(getChangePasswordSuccessMessage().getText().trim(), Constants.changePasswordSuccessfulMessage);
    }
}
