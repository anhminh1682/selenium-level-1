package com.tests.myticket;

import com.railway.constant.Constants;
import com.railway.pages.*;
import com.railway.utilities.Ticket;
import com.railway.utilities.enums.ProvincesEnums;
import com.railway.utilities.enums.SeatTypeEnums;
import com.tests.base.TestBase;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class TC16 extends TestBase {
    @Test
    public void userCanCancelATicket (){
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        TimetablePage timetablePage = new TimetablePage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        BookTicketSuccessfulPage bookTicketSuccessfulPage = new BookTicketSuccessfulPage();

        // 2. Login with a valid account
        homePage.clickOnTab(Constants.TabMenu.LOGIN_TAB);
        loginPage.login(Constants.Account.VALID_USERNAME, Constants.Account.VALID_PASSWORD);

        // 3. Book a ticket
        homePage.clickOnTab(Constants.TabMenu.BOOK_TICKET_TAB);

        Ticket ticket = new Ticket(
            LocalDateTime.now().plusDays(25).format(Constants.MyDateTimeFormat.M_d_yyyy),
            ProvincesEnums.HUE.getDisplayName(),
            ProvincesEnums.QUANG_NGAI.getDisplayName(),
            SeatTypeEnums.SOFT_BED_WITH_AIR_CONDITIONER.getDisplayName(),
            "1"
        );

        bookTicketPage.bookATicket(ticket);

        // 4. Click on "My ticket" tab
        bookTicketSuccessfulPage.clickOnTab(Constants.TabMenu.MY_TICKET_TAB);

        // 5. Click on "Cancel" button of ticket which user want to cancel.


        // 6. Click on "OK" button on Confirmation message "Are you sure?"

        // The canceled ticket is disappeared.
    }
}
