package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import com.tests.base.TestBase;
import org.testng.annotations.Test;

public class TC11 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty(String username, String password, String confirmPassword, String pid) {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Register' tab");
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);

        LogUtils.info("3. Enter valid email address and leave other fields empty");
        LogUtils.info("4. Click on 'Register' button");
        Account account = new Account(username, password, confirmPassword, pid);
        registerPage.registerUserAccount(account);

        softAssert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPasswordMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PASSWORD_LENGTH);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPIDMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PID_LENGTH);

        softAssert.assertAll();
    }
}
