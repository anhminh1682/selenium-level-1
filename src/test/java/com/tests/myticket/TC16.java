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

public class TC16 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanCancelATicket (
            String departStation,
            String arriveStation,
            Integer days,
            String seatTypes,
            Integer amount
    ) throws IOException {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        // 2. Login with a valid account
        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Website");
        LogUtils.info("2. Login with a valid account");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.loginSuccess();

        // 3. Book a ticket
        LogUtils.info("3. Book a ticket");
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

//        Ticket ticket = Helpers.getRandomTicket();
        Ticket ticket = new Ticket(
                days,
                StationEnum.fromStationName(departStation).getStationName(),
                StationEnum.fromStationName(arriveStation).getStationName(),
                SeatTypeEnum.fromSeatType(seatTypes).getSeatTypeName(),
                amount.toString()
        );

        bookTicketPage.bookATicket(ticket);

        // 4. Click on "My ticket" tab
        LogUtils.info("4. Click on 'My ticket' tab");
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
