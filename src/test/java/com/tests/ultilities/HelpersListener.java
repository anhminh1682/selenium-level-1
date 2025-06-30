package com.tests.ultilities;

import com.railway.driver.DriverManager;
import com.railway.utilities.LogUtils;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelpersListener {
    public static String TakeScreenshot(ITestResult result) {
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        String testName = result.getMethod().getMethodName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        String fileName = testName + "_" + timestamp + ".png";

        String baseDir = System.getProperty("user.dir");
        String failedDir = "test-images" + File.separator + result.getTestClass().getName();

        String relativeImagePath = failedDir + File.separator + fileName;
        String absolutePath = baseDir + File.separator + relativeImagePath;
        File destFile = new File(absolutePath);

        try {
            destFile.getParentFile().mkdirs();

            FileHandler.copy(srcFile, destFile);
            LogUtils.info("Screenshot saved to: " + absolutePath);

            byte[] fileContent = FileUtils.readFileToByteArray(destFile);

            return java.util.Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
