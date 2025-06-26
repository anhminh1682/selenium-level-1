package com.tests.myticket;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.Ticket;
import com.railway.utilities.enums.ProvincesEnums;
import com.railway.utilities.enums.SeatTypeEnums;
import com.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class TC16 extends TestBase {
    @Test
    public void userCanCancelATicket () throws IOException {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        // 2. Login with a valid account
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        // 3. Book a ticket
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Ticket ticket = Ticket.getRandomTicket();

        bookTicketPage.bookATicket(ticket);

        // 4. Click on "My ticket" tab
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
