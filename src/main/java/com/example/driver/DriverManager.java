package com.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver _driver;

    public static void createDriver() {
        if(_driver == null) {
            _driver = new ChromeDriver();
            _driver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return _driver;
    }

    public static void quitDriver() {
        _driver.quit();
    }
}
