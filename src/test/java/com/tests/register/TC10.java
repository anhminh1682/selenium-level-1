package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TC10 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.startStep("3. Register with valid information into all fields except 'Confirm password' is not the same with 'Password'");
        Account account = new Account(
                data.get(Constants.DataKeys.USERNAME).toString(),
                data.get(Constants.DataKeys.PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                data.get(Constants.DataKeys.PID).toString()
        );
        registerPage.registerUserAccount(account);
        Assert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
    }
}
