package com.tests.timetable;

import com.railway.constant.Constants;
import com.railway.pages.BookTicketPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.pages.TimetablePage;
import com.railway.utilities.LogUtils;
import com.railway.utilities.enums.ProvincesEnums;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC15 extends TestBase {
    @Test
    public void userCanOpenBookTicketPageByClickingOnBookTicketLinkInTrainTimetablePage() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        TimetablePage timetablePage = new TimetablePage();
        BookTicketPage bookTicketPage = new BookTicketPage();

        // 2. Login with a valid account
        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Website");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("2. Login with a valid account");
        loginPage.loginSuccess();

        // 3. Click on "Timetable" tab
        LogUtils.info("3. Click on 'Timetable' tab");
        homePage.clickOnTab(Constants.TabMenu.TIMETABLE_TAB);

        // 4. Click on "book ticket" link of the route from "Huế" to "Sài Gòn"
        LogUtils.info("4. Click on 'book ticket' link of the route from 'Huế' to 'Sài Gòn'");
        timetablePage.clickOnBookTicketButton(ProvincesEnums.HUE.getDisplayName(), ProvincesEnums.SAI_GON.getDisplayName());

        // "Book ticket" page is loaded with correct "Depart from" and "Arrive at" values.
        Assert.assertEquals(
                bookTicketPage.getCurrentValueOfASelectBox(Constants.BookTicket.DEPART_FROM_NAME_SELECT_BOX),
                ProvincesEnums.HUE.getDisplayName()
        );
        Assert.assertEquals(
                bookTicketPage.getCurrentValueOfASelectBox(Constants.BookTicket.ARRIVE_AT_NAME_SELECT_BOX),
                ProvincesEnums.SAI_GON.getDisplayName()
        );
    }
}
