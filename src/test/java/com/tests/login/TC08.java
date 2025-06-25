package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.pages.RegisterPage;
import com.railway.pages.ThankRegisterPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08 extends TestBase {
    @Test
    public void userCannotLoginWithAnAccountHasNotBeenActivated() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // Login
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.INACTIVE_USERNAME_LOGIN, Constants.Account.INACTIVE_USERNAME_LOGIN);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_INVALID_FIELDS);
    }
}
