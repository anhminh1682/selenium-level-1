package com.tests.myticket;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.LogUtils;
import com.railway.utilities.Ticket;
import com.railway.utilities.enums.SeatTypeEnum;
import com.railway.utilities.enums.StationEnum;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TC16 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanCancelATicket (Map<String, Object> data) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        // 2. Login with a valid account
        LogUtils.startStep("Pre-condition: Create and activate a new account");
        LogUtils.startStep("1. Navigate to QA Railway Website");
        LogUtils.startStep("2. Login with a valid account");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.loginSuccess();

        // 3. Book a ticket
        LogUtils.startStep("3. Book a ticket");
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Ticket ticket = new Ticket(
                Integer.parseInt(data.get(Constants.DataKeys.DAYS).toString()),
                StationEnum.fromStationName(data.get(Constants.DataKeys.DEPART_STATION).toString()).getStationName(),
                StationEnum.fromStationName(data.get(Constants.DataKeys.ARRIVE_STATION).toString()).getStationName(),
                SeatTypeEnum.fromSeatType(data.get(Constants.DataKeys.SEAT_TYPES).toString()).getSeatTypeName(),
                data.get(Constants.DataKeys.AMOUNT).toString()
        );

        bookTicketPage.bookATicket(ticket);

        // 4. Click on "My ticket" tab
        LogUtils.startStep("4. Click on 'My ticket' tab");
        bookTicketSuccessfulPage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);

        // Count the amount of tickets before cancel
        int oldCount = myTicketPage.countOfRowTicket(ticket);

        // 5. Click on "Cancel" button of ticket which user want to cancel.
        // 6. Click on "OK" button on Confirmation message "Are you sure?"
        myTicketPage.cancelATicket(ticket);

        // Count the amount of tickets after cancel
        int newCount = myTicketPage.countOfRowTicket(ticket);

        // The canceled ticket is disappeared.
        Assert.assertEquals(newCount, oldCount - 1);
    }
}
