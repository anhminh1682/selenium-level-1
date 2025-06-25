package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.RegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08 extends TestBase {
    @Test
    public void userCannotLoginWithAnAccountHasNotBeenActivated() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        String newEmail = Constants.Account.IN_ACTIVE_USERNAME;

        // Register
        homePage.clickOnTab(Constants.TabMenu.REGISTER_TAB);
        registerPage.registerUserAccount(newEmail, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PASSWORD, Constants.Account.VALID_PID);
        Assert.assertEquals(registerPage.getRegisterValidInforHeading(), Constants.RegisterMessage.REGISTER_VALID_INFOR_HEADING);

        // Login
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(newEmail, Constants.Account.INVALID_PASSWORD);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_INVALID_FIELDS);
    }
}
