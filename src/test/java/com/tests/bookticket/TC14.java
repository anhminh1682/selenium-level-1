package com.tests.bookticket;

import com.railway.constant.Constants;
import com.railway.pages.BookTicketPage;
import com.railway.pages.BookTicketSuccessfulPage;
import com.railway.pages.HomePage;
import com.railway.pages.LoginPage;
import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import com.railway.utilities.Ticket;
import com.railway.utilities.enums.ProvincesEnums;
import com.railway.utilities.enums.SeatTypeEnums;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class TC14 extends TestBase {
    @Test
    public void userCanBookOneTicketAtATime() throws IOException {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();

        LogUtils.info("Pre-condition: Create and activate a new account");
        LogUtils.info("1. Navigate to QA Railway Website");
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);

        LogUtils.info("2. Login with a valid account");
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        LogUtils.info("3. Click on 'Book ticket' tab");
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Ticket ticket = Helpers.getRandomTicket();

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
    }
}
