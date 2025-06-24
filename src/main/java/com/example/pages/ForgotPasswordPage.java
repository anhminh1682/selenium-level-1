package com.example.pages;

import com.example.driver.DriverManager;
import com.example.utilities.MailBoxManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ForgotPasswordPage {
    private final By emailTextBox = By.id("email");
    private final By submitButton = By.xpath("//input[@type='submit']");

    private WebElement getEmailTextBox() {
        return DriverManager.getDriver().findElement(emailTextBox);
    }

    private WebElement getSubmitButton() {
        return DriverManager.getDriver().findElement(submitButton);
    }

    public void sendInstructions(String email) {
        getEmailTextBox().sendKeys(email);

        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, getSubmitButton().getRect().y)
                .perform();

        getSubmitButton().click();
    }

    public void goToMailBox() {
        DriverManager.getDriver().get(MailBoxManager.getMailBoxURL());
    }
}
