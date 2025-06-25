package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.pages.ThankRegisterPage;
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
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        registerPage.registerUserAccount(Constants.Account.INACTIVE_USERNAME_REGISTER, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PID);

        Assert.assertEquals(thankRegisterPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);
    }
}
