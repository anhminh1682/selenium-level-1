package com.railway.utilities;

import com.railway.utilities.enums.AccountEnum;
import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.*;

public class MailBoxManager {
    private static final String mailBoxURL = "https://www.guerrillamail.com";
    private static final By emailTextBox = By.id("inbox-id");
    private static final By emailTextBoxEditable = By.xpath("//span[contains(@class, 'edit-in-progress')]/input");
    private static final By emailDomainSelectBox = By.id("gm-host-select");
    private static final By setButton = By.xpath("//button[@class='save button small']");
    private static final By emailConfirmUnread = By.xpath("//tr[contains(@class, 'email_unread')]//td[contains(text(),'thanhletraining03@gmail.com')][1]");
    private static final By resetPasswordLink = By.partialLinkText("http://www.saferailway.somee.com/Account/PasswordReset");

    public static String getMailBoxURL() {
        return mailBoxURL;
    }

    public static void clickResetPasswordLink() {
        String validEmail = AccountEnum.VALID_ACCOUNT_LOGIN.getUsername();
        String[] emailSplit = validEmail.split("@");

        webElement(emailTextBox).click();
        webElement(emailTextBoxEditable).sendKeys(emailSplit[0]);
        webElement(setButton).click();

        selectElementByVisibleText(emailDomainSelectBox, emailSplit[1]);

        waitForElement(15, emailConfirmUnread);
        webElement(emailConfirmUnread).click();

        waitForElement(15, resetPasswordLink);
        webElement(resetPasswordLink).click();
    }
}
