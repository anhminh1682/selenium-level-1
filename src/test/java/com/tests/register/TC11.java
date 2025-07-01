package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.tests.base.TestBase;
import org.testng.annotations.Test;

import java.util.Map;

public class TC11 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.startStep("3. Register with valid email address and leave other fields empty");
        Account account = new Account(
                data.get(Constants.DataKeys.USERNAME).toString(),
                data.get(Constants.DataKeys.PASSWORD).toString(),
                data.get(Constants.DataKeys.CONFIRM_PASSWORD).toString(),
                data.get(Constants.DataKeys.PID).toString()
        );
        registerPage.registerUserAccount(account);

        softAssert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPasswordMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PASSWORD_LENGTH);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPIDMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PID_LENGTH);

        softAssert.assertAll();
    }
}
