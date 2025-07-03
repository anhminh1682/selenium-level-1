package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.DriverUtils;
import com.railway.utilities.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.railway.utilities.DriverUtils.getWebElement;

public class BasePage {
    private final String tabXpath = "//a//span[text()='%s']";
    private final By welcomeText = By.xpath("//div[@class='account']/strong");

    private WebElement getTab(String tabName) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(tabXpath, tabName)));
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
        DriverUtils.waitForElement(3, By.xpath(String.format(tabXpath, tabName)));

        LogUtils.info("Click on tab " + tabName);
        getTab(tabName).click();
    }

    public String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public String getWelComeText() {
        return getWebElement(welcomeText).getText();
    }
}
