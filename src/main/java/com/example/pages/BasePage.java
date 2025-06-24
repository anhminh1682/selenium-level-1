package com.example.pages;

import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    private final String tabXpath = "//a//span[text()='%s']";

    private WebElement getTab(String tab) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(tabXpath, tab)));
    }

    public boolean checkMyTicketTabDisplayed() {
        return getTab("My ticket").isDisplayed();
    }

    public boolean checkChangePasswordTabDisplayed() {
        return getTab("Change password").isDisplayed();
    }

    public boolean checkLogOutTabDisplayed() {
        return getTab("Log out").isDisplayed();
    }

    public void clickToTab(String tab) {
        getTab(tab).click();
    }
}
