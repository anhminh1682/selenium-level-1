package com.tests.login;

import com.example.constant.Constants;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02 extends TestBase {
    @Test
    public void userCannotLoginWithBlankUsernameTextbox() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        homePage.clickOnTab("Login");
        loginPage.login("", Constants.Account.VALID_PASSWORD);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD);
    }
}
