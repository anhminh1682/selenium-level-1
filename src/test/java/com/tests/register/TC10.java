package com.tests.register;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10 extends TestBase {
    @Test
    public void userCannotCreateAccountWithConfirmPasswordIsNotTheSameWithPassword() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        homePage.clickOnTab("Register");
        registerPage.registerUserAccount(Constants.Account.IN_ACTIVE_USERNAME, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD + "1", Constants.Account.VALID_PID);
        Assert.assertEquals(registerPage.getErrorRegisterMessage(), Constants.RegisterMessage.ERROR_REGISTER_WITH_INVALID_INFO);
    }
}
