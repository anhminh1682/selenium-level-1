package com.tests.ultilities;

import com.railway.driver.DriverManager;
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

        String relativeImagePath = baseDir + File.separator + failedDir + File.separator + fileName;
        File destFile = new File(baseDir + File.separator + relativeImagePath);

        try {
            boolean dirExist = destFile.getParentFile().mkdirs();

            if(dirExist) {
                FileHandler.copy(srcFile, destFile);

                byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
                String base64Image = java.util.Base64.getEncoder().encodeToString(fileContent);

                return base64Image;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativeImagePath;
    }
}
