package com.example.pages;

import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    private final By registerTab = By.xpath("//a//span[text()='Register']");
    private final By loginTab = By.xpath("//a//span[text()='Login']");
    private final By bookTicketTab = By.xpath("//a//span[text()='Book ticket']");
    private final By myTicketTab = By.xpath("//a//span[text()='My ticket']");

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
}
