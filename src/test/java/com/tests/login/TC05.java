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

public class TC05 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void systemShowsMessageWhenUserEntersWrongPasswordSeveralTimes(Map<String, Object> data) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        int n = 4;

        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Click on 'Login' tab");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.startStep("3. Login with valid information into 'Username' textbox except 'Password' textbox and repeat three more times.");
        Account account = new Account(data.get(Constants.DataKeys.USERNAME).toString(), data.get(Constants.DataKeys.PASSWORD).toString());
        loginPage.loginMultipleTimes(n, account);

        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LoginMessage.WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH);
    }
}
