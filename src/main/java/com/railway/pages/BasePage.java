package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    private final String tabXpath = "//a//span[text()='%s']";

    private WebElement getTab(String tabName) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(tabXpath, tabName)));
    }

    protected static WebElement webElement(By element) {
        return DriverManager.getDriver().findElement(element);
    }

    public boolean isMyTicketTabDisplayed() {
        return getTab(Constants.TabMenu.MY_TICKET_TAB).isDisplayed();
    }

    public boolean isChangePasswordTabDisplayed() {
        return getTab(Constants.TabMenu.CHANGE_PASSWORD_TAB).isDisplayed();
    }

    public boolean isLogOutTabDisplayed() {
        return getTab(Constants.TabMenu.LOG_OUT_TAB).isDisplayed();
    }

    public void clickOnTab(String tabName) {
        Helpers.waitForElement(3, By.xpath(String.format(tabXpath, tabName)));

        LogUtils.info("Click on tab " + tabName);
        getTab(tabName).click();
    }

    public String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public static String getElementText(WebElement element) {
        return element.getText().trim();
    }
}
