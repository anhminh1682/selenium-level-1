package com.tests.base;

import com.example.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class TestBase {
    protected SoftAssert softAssert;

    public TestBase() {
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeTest() {
        DriverManager.createDriver();
        DriverManager.getDriver().get("http://saferailway.somee.com/");
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
    }
}
