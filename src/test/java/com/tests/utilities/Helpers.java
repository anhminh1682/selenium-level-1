package com.tests.utilities;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import com.tests.utilities.MailHelpers.MailDataFromJSON;
import com.tests.utilities.MailHelpers.MailHelpers;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Helpers {
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

    public static String registerNewAccountUsingAPI() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        MailDataFromJSON mailDataFromJSON = new MailDataFromJSON();
        MailHelpers mailHelpers = new MailHelpers();
        Map<String, String> emailDatas = mailDataFromJSON.mailData();

        String emailAddress = emailDatas.get("emailAddress");
        String inboxId = emailDatas.get("inboxId");

        boolean isEmailExpired = mailHelpers.callToCheckInboxIdExpired(inboxId);

        if(isEmailExpired || Objects.equals(emailAddress, "") || Objects.equals(inboxId, "") ) {
            String[] emailInfos = mailHelpers.callToCreateInbox();
            emailAddress = emailInfos[0];
            inboxId = emailInfos[1];
        }
        mailDataFromJSON.writeDataToJsonFile(inboxId, emailAddress);

        String [] emailSplit = emailAddress.split("@");
        emailAddress = emailSplit[0] + "+" + com.railway.utilities.Helpers.randomBase62(4) + "@" + emailSplit[1];

        LogUtils.startStep("Pre-condition: Create and activate a new account");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        Account accountRegister = new Account(
                emailAddress,
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPassword(),
                AccountEnum.VALID_ACCOUNT_REGISTER.getPID()
        );

        registerPage.registerUserAccount(accountRegister);

        String linkRegister = mailHelpers.getLinkInEmailByAPI(Constants.MailSlurp.EMAIL_CONFIRM_SUBJECT + " " + emailAddress);

        DriverManager.getDriver().get(linkRegister);

        return emailAddress;
    }
}
