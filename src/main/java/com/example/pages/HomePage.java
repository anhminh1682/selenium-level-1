package com.example.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By welcomeText = By.xpath("//div[@class='account']/strong");

    public String getWelComeText() {
        return webElement(welcomeText).getText();
    }
}
