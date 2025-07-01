package com.railway.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    private static ThreadLocal<ExtentTest> mainTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> currentStep = new ThreadLocal<>();

    public static ExtentTest getExtentTest() {
        return currentStep.get();
    }

    public static void setExtentTest(ExtentTest test) {
        mainTest.set(test);
        currentStep.set(test);
    }

    public static void startStep(String stepName) {
        ExtentTest step = mainTest.get().createNode(stepName);
        currentStep.set(step);
        logger.info("Step " + stepName);
    }

    // Info Level Logs
    public static void info(Object object) {
        logger.info(object);
        getExtentTest().log(Status.INFO, object.toString());
    }

    // Warn Level Logs
    public static void warn(Object object) {
        logger.warn(object);
        getExtentTest().log(Status.WARNING, object.toString());
    }

    // Error Level Logs
    public static void error(Object object) {
        logger.error(object);
        getExtentTest().log(Status.FAIL, object.toString());
    }

    // Fatal Level Logs
    public static void fatal(Object object) {
        logger.fatal(object);
    }

    // Debug Level Logs
    public static void debug(Object object) {
        logger.debug(object);
    }
}
