package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import com.railway.utilities.MailBoxManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends BasePage {
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

        Helpers.scrollToElement(getSubmitButton());

        getSubmitButton().click();
    }

    public void goToMailBox() {
        DriverManager.getDriver().get(MailBoxManager.getMailBoxURL());
    }
}
