package com.railway.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    // Info Level Logs
    public static void info(Object object) {
        logger.info(object);
    }

    // Warn Level Logs
    public static void warn(Object object) {
        logger.warn(object);
    }

    // Error Level Logs
    public static void error(Object object) {
        logger.error(object);
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
