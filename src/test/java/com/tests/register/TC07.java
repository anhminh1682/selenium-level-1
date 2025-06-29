package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.pages.ThankRegisterPage;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07 extends TestBase {
    @Test
    public void userCanCreateNewAccount() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        ThankRegisterPage thankRegisterPage = new ThankRegisterPage();

        // Register
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.info("3. Enter valid information into all fields");
        LogUtils.info("4. Click on 'Register' button");
        registerPage.registerWithValidInfo();

        Assert.assertEquals(thankRegisterPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);
    }
}
