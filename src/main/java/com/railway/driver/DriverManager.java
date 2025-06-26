package com.railway.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> _driver = new ThreadLocal<>();

    public static void createDriver() {
        if(_driver.get() == null) {
            WebDriver webDriver = new ChromeDriver();
            _driver.set(webDriver);
            _driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return _driver.get();
    }

    public static void quitDriver() {
        _driver.get().quit();
        _driver.remove();
    }
}
