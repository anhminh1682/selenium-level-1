package com.railway.utilities;

import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {
    public static void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, element.getRect().y)
                .perform();
    }

    public static void waitForElement(int duration, By elementBy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }
}
