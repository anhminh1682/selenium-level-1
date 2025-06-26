package com.tests.ultilities;

import com.railway.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        String testName = result.getMethod().getMethodName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        String fileName = testName + "_" + timestamp + ".png";

        String baseDir = System.getProperty("user.dir");
        String failedDir = "test-images/" + result.getTestClass().getName();

        File destFile = new File(baseDir + File.separator + failedDir + File.separator + fileName);

        try {
            boolean dirExist = destFile.getParentFile().mkdirs();

            if(dirExist) {
                FileHandler.copy(srcFile, destFile);
                System.out.println("📸 screenshot saved to: " + destFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
