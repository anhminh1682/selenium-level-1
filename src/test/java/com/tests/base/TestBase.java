package com.tests.base;

import com.example.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    @BeforeMethod
    public void beforeTest() {
        DriverManager.createDriver();
        DriverManager.getDriver().get("http://saferailway.somee.com/");
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.getDriver().quit();
    }
}
