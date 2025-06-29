package com.railway.utilities;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.enums.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class MailBoxManager {
    private static final String mailBoxURL = "https://www.guerrillamail.com";
    private static final By emailTextBox = By.id("inbox-id");
    private static final By emailTextBoxEditable = By.xpath("//span[contains(@class, 'edit-in-progress')]/input");
    private static final By emailDomainSelectBox = By.id("gm-host-select");
    private static final By setButton = By.xpath("//button[@class='save button small']");
    private static final By emailConfirmUnread = By.xpath("//tr[contains(@class, 'email_unread')]//td[contains(text(),'thanhletraining03@gmail.com')][1]");
    private static final By resetPasswordLink = By.partialLinkText("http://www.saferailway.somee.com/Account/PasswordReset");

    private static WebElement getEmailTextBox() {
        return DriverManager.getDriver().findElement(emailTextBox);
    }

    private static WebElement getEmailTextBoxEditable() {
        return DriverManager.getDriver().findElement(emailTextBoxEditable);
    }

    private static WebElement getEmailDomainSelectBox() {
        return DriverManager.getDriver().findElement(emailDomainSelectBox);
    }

    private static WebElement getSetButton() {
        return DriverManager.getDriver().findElement(setButton);
    }

    private static WebElement getEmailConfirmUnread() {
        return DriverManager.getDriver().findElement(emailConfirmUnread);
    }

    private static WebElement getResetPasswordLink() {
        return DriverManager.getDriver().findElement(resetPasswordLink);
    }

    public static String getMailBoxURL() {
        return mailBoxURL;
    }

    public static void clickResetPasswordLink() {
        String validEmail = Account.VALID_ACCOUNT_LOGIN.getUsername();
        String[] emailSplit = validEmail.split("@");

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));

        getEmailTextBox().click();
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBoxEditable));
        getEmailTextBoxEditable().sendKeys(emailSplit[0]);
        getSetButton().click();

        Select emailDomainSelect = new Select(getEmailDomainSelectBox());
        emailDomainSelect.selectByVisibleText(emailSplit[1]);

        wait.until(ExpectedConditions.elementToBeClickable(emailConfirmUnread));
        getEmailConfirmUnread().click();

        wait.until(ExpectedConditions.elementToBeClickable(resetPasswordLink));
        getResetPasswordLink().click();
    }
}
