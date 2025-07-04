package com.tests.utilities.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.railway.constant.Constants;

import java.time.LocalDateTime;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    public static void createExtentReport() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("logs/reports/ExtentReport.html" + LocalDateTime.now().format(Constants.MyDateTimeFormat.M_d_yyyy_HH_mm_ss));
        extent.attachReporter(spark);
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void flushExtent() {
        extent.flush();
    }
}
