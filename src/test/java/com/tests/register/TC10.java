package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10 extends TestBase {
    @Test
    public void userCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.info("3. Enter valid information into all fields except 'Confirm password' is not the same with 'Password'");
        LogUtils.info("4. Click on 'Register' button");
        registerPage.registerUserAccount(Constants.Account.INACTIVE_USERNAME_REGISTER, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD + "1", Constants.Account.VALID_PID);
        Assert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
    }
}
