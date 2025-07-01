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

import java.util.Map;

public class TC03 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void UserCannotLogIntoRailwayWithInvalidPassword(Map<String, Object> data) {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();

        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("3. Enter valid Email and invalid Password");
        LogUtils.info("4. Click on 'Login' button");
        Account account = new Account(data.get(Constants.DataKeys.USERNAME).toString(), data.get(Constants.DataKeys.PASSWORD).toString());
        loginPage.login(account);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD);
    }
}
