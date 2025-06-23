package com.example.pages;

import com.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class HomePage extends BasePage {
    private final By welcomeText = By.xpath("//div[@class='account']/strong");

    private WebElement getWelcomeText() {
        return DriverManager.getDriver().findElement(welcomeText);
    }

    public boolean checkWelcomeTextAfterLoginSuccess(String username) {
        return Objects.equals(getWelcomeText().getText(), "Welcome " + username);
    }
}
