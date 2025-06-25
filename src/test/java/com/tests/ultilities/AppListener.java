package com.tests.ultilities;

import com.railway.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class AppListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File destFile = new File("failure.png");

        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
