package com.tests.base;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.tests.ultilities.AppListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners(AppListener.class)
public class TestBase {
    protected SoftAssert softAssert;

    public TestBase() {
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeTest() {
        DriverManager.createDriver();
        DriverManager.getDriver().get(Constants.URL.RAILWAY_URL);
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
    }
}
