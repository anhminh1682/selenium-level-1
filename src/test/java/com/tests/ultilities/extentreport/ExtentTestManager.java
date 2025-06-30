package com.tests.ultilities.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports extent = ExtentReport.getExtent();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }
}
