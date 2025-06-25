package com.example.utilities;

import com.example.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helpers {
    public static void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, element.getRect().y)
                .perform();
    }
}
