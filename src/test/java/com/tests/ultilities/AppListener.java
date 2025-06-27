package com.tests.ultilities;

import com.aventstack.extentreports.Status;
import com.railway.utilities.LogUtils;
import com.tests.ultilities.extentreport.ExtentReport;
import com.tests.ultilities.extentreport.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class AppListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        ExtentReport.createExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.error(result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error(result.getThrowable());
        String imagePath = HelpersListener.TakeScreenshot(result);

        ExtentTestManager.getTest().addScreenCaptureFromBase64String(imagePath);
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.flushExtent();
    }
}
