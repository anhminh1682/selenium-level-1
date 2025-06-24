package com.example.pages;

import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    private final By registerTab = By.xpath("//a//span[text()='Register']");
    private final By loginTab = By.xpath("//a//span[text()='Login']");
    private final By bookTicketTab = By.xpath("//a//span[text()='Book ticket']");
    private final By myTicketTab = By.xpath("//a//span[text()='My ticket']");
    private final By logOutTab = By.xpath("//a//span[text()='Log out']");
    private final By changePasswordTab = By.xpath("//a//span[text()='Change password']");

    private WebElement getRegisterTab() {
        return DriverManager.getDriver().findElement(registerTab);
    }

    private WebElement getLoginTab() {
        return DriverManager.getDriver().findElement(loginTab);
    }

    private WebElement getBookTicketTab() {
        return DriverManager.getDriver().findElement(bookTicketTab);
    }

    private WebElement getMyTicketTab() {
        return DriverManager.getDriver().findElement(myTicketTab);
    }

    private WebElement getChangePasswordTab() {
        return DriverManager.getDriver().findElement(changePasswordTab);
    }

    private WebElement getLogOutTab() {
        return DriverManager.getDriver().findElement(logOutTab);
    }

    public boolean checkMyTicketTabDisplayed() {
        return DriverManager.getDriver().findElement(myTicketTab).isDisplayed();
    }

    public boolean checkChangePasswordTabDisplayed() {
        return DriverManager.getDriver().findElement(changePasswordTab).isDisplayed();
    }

    public boolean checkLogOutTabDisplayed() {
        return DriverManager.getDriver().findElement(logOutTab).isDisplayed();
    }

    public void goToRegisterPage() {
        getRegisterTab().click();
    }

    public void goToLoginPage() {
        getLoginTab().click();
    }

    public void goToBookTicketPage() {
        getBookTicketTab().click();
    }

    public void goToMyTicketTab() {
        getMyTicketTab().click();
    }

    public void goToChangePassword() {
        getChangePasswordTab().click();
    }

    public void logOut() {
        getLogOutTab().click();
    }
}
