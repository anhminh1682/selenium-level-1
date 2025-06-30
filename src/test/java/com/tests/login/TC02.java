package com.tests.login;

import com.railway.constant.Constants;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.Account;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.AccountEnum;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCannotLoginWithBlankUsernameTextbox(String username, String password) {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. User doesn't type any words into 'Username' textbox but enter valid information into 'Password' textbox");
        LogUtils.info("4. Click on 'Login' button");
        Account account = new Account(username, password);
        loginPage.login(account);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD);
    }
}
