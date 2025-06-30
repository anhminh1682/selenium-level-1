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

public class TC07 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanCreateNewAccount(String username, String password, String confirmPassword, String pid) {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        ThankRegisterPage thankRegisterPage = new ThankRegisterPage();

        // Register
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.info("3. Enter valid information into all fields");
        LogUtils.info("4. Click on 'Register' button");
        Account account = new Account(
                LocalDateTime.now().format(Constants.MyDateTimeFormat.HH_mm_ss) + username,
                password,
                confirmPassword,
                pid
        );
        registerPage.registerUserAccount(account);

        Assert.assertEquals(thankRegisterPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);
    }
}
