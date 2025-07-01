package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.pages.ThankRegisterPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Map;

public class TC07 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanCreateNewAccount(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        ThankRegisterPage thankRegisterPage = new ThankRegisterPage();

        // Register
        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.startStep("3. Register with valid info");
        Account account = new Account(
                LocalDateTime.now().format(Constants.MyDateTimeFormat.HH_mm_ss)
                        + data.get(Constants.DataKeys.USERNAME).toString(),
                data.get(Constants.DataKeys.PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                data.get(Constants.DataKeys.PID).toString()
        );
        registerPage.registerUserAccount(account);

        Assert.assertEquals(thankRegisterPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);
    }
}
