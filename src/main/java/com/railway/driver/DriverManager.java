package com.railway.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> _driver = new ThreadLocal<>();

    public static void createDriver() {
        if(_driver.get() == null) {
//            ChromeOptions options = new ChromeOptions();
//            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            WebDriver webDriver = new ChromeDriver();
//            ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
//            networkConditions.setOffline(false);
//            networkConditions.setLatency(Duration.ofMillis(20)); // 20 ms of latency
//            networkConditions.setDownloadThroughput(100 * 1024 / 8); // 2000 kbps
//            networkConditions.setUploadThroughput(100 * 1024 / 8);   // 2000 kbps

//            ((ChromeDriver) webDriver).setNetworkConditions(networkConditions);

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
