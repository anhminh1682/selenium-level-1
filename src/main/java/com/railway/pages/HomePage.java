package com.railway.pages;

import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.webElement;

public class HomePage extends BasePage {
    private final By welcomeText = By.xpath("//div[@class='account']/strong");

    public String getWelComeText() {
        return webElement(welcomeText).getText();
    }
}
