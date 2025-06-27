package com.tests.ultilities.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    public static void createExtentReport() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent.attachReporter(spark);
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void flushExtent() {
        extent.flush();
    }
}
