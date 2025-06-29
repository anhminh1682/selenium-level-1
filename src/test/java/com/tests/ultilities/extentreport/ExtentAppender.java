package com.tests.ultilities.extentreport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "ExtentAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class ExtentAppender extends AbstractAppender {

    protected ExtentAppender(String name) {
        super(name, null, null, true, null);
    }

    @PluginFactory
    public static ExtentAppender createAppender() {
        return new ExtentAppender("ExtentAppender");
    }

    @Override
    public void append(LogEvent event) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            String message = event.getMessage().getFormattedMessage();
            switch (event.getLevel().toString()) {
                case "INFO":
                    test.log(Status.INFO, message);
                    break;
                case "WARN":
                    test.log(Status.WARNING, message);
                    break;
                case "DEBUG":
                case "FATAL":
                case "ERROR":
                    test.log(Status.FAIL, message);
                    break;
                default:
                    test.log(Status.INFO, message);
                    break;
            }
        }
    }
}
