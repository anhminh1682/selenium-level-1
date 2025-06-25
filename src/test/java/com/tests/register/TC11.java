package com.tests.register;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.annotations.Test;

public class TC11 extends TestBase {
    @Test
    public void userCannotCreateAccountWhilePasswordAndPIDFieldsAreEmpty() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        registerPage.registerUserAccount(Constants.Account.VALID_USERNAME, "", "", "");

        softAssert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPasswordMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PASSWORD_LENGTH);
        softAssert.assertEquals(registerPage.getRegisterFailedWithInvalidPIDMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_PID_LENGTH);

        softAssert.assertAll();
    }
}
