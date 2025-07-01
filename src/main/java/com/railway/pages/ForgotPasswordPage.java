package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.DriverUtils;
import com.railway.utilities.MailBoxManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.railway.utilities.DriverUtils.clickOnElement;
import static com.railway.utilities.DriverUtils.webElement;

public class ForgotPasswordPage extends BasePage {
    private final By emailTextBox = By.id("email");
    private final By submitButton = By.xpath("//input[@type='submit']");

    public void sendInstructions(String email) {
        webElement(emailTextBox).sendKeys(email);

        clickOnElement(submitButton);
    }

    public void goToMailBox() {
        DriverManager.getDriver().get(MailBoxManager.getMailBoxURL());
    }
}
