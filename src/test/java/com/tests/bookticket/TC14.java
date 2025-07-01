package com.tests.bookticket;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.LogUtils;
import com.railway.utilities.Ticket;
import com.railway.utilities.enums.SeatTypeEnum;
import com.railway.utilities.enums.StationEnum;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC14 extends TestBase {
    @Test(dataProvider = "dataTestProvider", dataProviderClass = TestBase.class)
    public void userCanBookOneTicketAtATime(
            String departStation,
            String arriveStation,
            Integer days,
            String seatTypes,
            Integer amount
    ) {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Website");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("2. Login with a valid account");
        loginPage.loginSuccess();

        // Check the number of ticket rows before booking a ticket
//        Ticket ticket = Helpers.getRandomTicket();
        Ticket ticket = new Ticket(
                days,
                StationEnum.fromStationName(departStation).getStationName(),
                StationEnum.fromStationName(arriveStation).getStationName(),
                SeatTypeEnum.fromSeatType(seatTypes).getSeatTypeName(),
                amount.toString()
        );

        homePage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);
        int countTicketBeforeAdd = myTicketPage.countOfRowTicket(ticket);

        LogUtils.info("3. Click on 'Book ticket' tab");
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        bookTicketPage.bookATicket(ticket);

        // Check Message "Ticket booked successfully!" displays
        Assert.assertEquals(bookTicketSuccessfulPage.getPageTitle(), Constants.PageTitles.BOOK_TICKET_SUCCESS_PAGE_TITLE);
        Assert.assertEquals(bookTicketSuccessfulPage.getBookSuccessHeadingMessage(), Constants.BookTicketMessage.SUCCESSFUL_MESSAGE);

        // Check information display correctly (Depart Date, Depart Station, Arrive Station, Seat Type, Amount)
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.DEPART_STATION_COLUMN_NAME),
                ticket.getDepartStation()
        );
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.ARRIVE_STATION_COLUMN_NAME),
                ticket.getArriveStation()
        );
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.SEAT_TYPE_COLUMN_NAME),
                ticket.getSeatType()
        );
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.DEPART_DATE_COLUMN_NAME),
                ticket.getDepartDate()
        );
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.AMOUNT_COLUMN_NAME),
                ticket.getTicketAmount()
        );

        // Check the number of ticket rows after booking a ticket
        homePage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);
        int countTicketAfterAdd = myTicketPage.countOfRowTicket(ticket);

        Assert.assertEquals(countTicketBeforeAdd, countTicketAfterAdd - 1);
    }
}
