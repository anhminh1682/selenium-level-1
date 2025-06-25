package com.tests.register;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07 extends TestBase {
    @Test
    public void userCanCreateNewAccount() {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();

        // Register
        homePage.clickOnTab("Register");
        registerPage.registerUserAccount(Constants.Account.IN_ACTIVE_USERNAME, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PID);

        Assert.assertEquals(registerPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);
    }
}
