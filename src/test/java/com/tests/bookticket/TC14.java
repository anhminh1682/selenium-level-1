package com.tests.bookticket;

import com.railway.constant.Constants;
import com.railway.pages.BookTicketPage;
import com.railway.pages.BookTicketSuccessfulPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.Ticket;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC14 extends TestBase {
    @Test
    public void userCanBookOneTicketAtATime() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();

        // Login
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        // Book ticket
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Ticket ticket = new Ticket(
            Constants.BookTicket.DEPART_DATE_VALUE,
            Constants.BookTicket.DEPART_FROM_VALUE,
            Constants.BookTicket.ARRIVE_AT_VALUE,
            Constants.BookTicket.SEAT_TYPE_VALUE,
            Constants.BookTicket.TICKET_AMOUNT_VALUE
        );

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
                ticket.getDate()
        );
        Assert.assertEquals(
                bookTicketSuccessfulPage.getTicketValueInAColumn(Constants.BookTicketSuccess.AMOUNT_COLUMN_NAME),
                ticket.getTicketAmount()
        );
    }
}
