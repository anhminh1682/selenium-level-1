package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.DriverUtils;
import com.railway.utilities.LogUtils;
import com.railway.utilities.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.railway.utilities.DriverUtils.*;

public class BookTicketPage extends BasePage {
    private final String selectBoxXpath = "//select[@name='%s']";
    private final String currentOptionOfSelectBoxXpath = "//select[@name='%s']/option[@selected='selected']";
    private final String listOptionsOfSelectBoxXpath = "//select[@name='%s']/option";

    private final By bookTicketButton = By.xpath("//input[@value='Book ticket']");

    private WebElement getSelectBoxByName(String name) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(selectBoxXpath, name)));
    }

    private WebElement getCurrentOptionOfSelectBoxXpath(String name) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(currentOptionOfSelectBoxXpath, name)));
    }

    private List<WebElement> getListOptionsOfSelectBox(String name) {
        return DriverManager.getDriver().findElements(By.xpath(String.format(listOptionsOfSelectBoxXpath, name)));
    }

    public String getCurrentValueOfASelectBox(String name) {
        return getElementText(getCurrentOptionOfSelectBoxXpath(name));
    }

    public void selectAnOption(String selectBoxName, String option) {
        Select selectBox = new Select(getSelectBoxByName(selectBoxName));
        selectBox.selectByVisibleText(option);
    }

    public void bookATicket(Ticket ticket) {
        scrollToElement(getSelectBoxByName(Constants.BookTicket.DEPART_DATE_NAME_SELECT_BOX));

        LogUtils.info("Select a 'Depart date' from the list");
        selectAnOption(Constants.BookTicket.DEPART_DATE_NAME_SELECT_BOX, ticket.getDepartDate());

        LogUtils.info("Select " + ticket.getDepartStation() + " for 'Depart from' and " + ticket.getArriveStation() + " for 'Arrive at'.");
        List<WebElement> oldArriveAtOptionList = getListOptionsOfSelectBox(Constants.BookTicket.ARRIVE_AT_NAME_SELECT_BOX);
        selectAnOption(Constants.BookTicket.DEPART_FROM_NAME_SELECT_BOX, ticket.getDepartStation());

        // Wait for arrive at select box update DOM
        waitForDynamicElement(10, String.format(listOptionsOfSelectBoxXpath, Constants.BookTicket.ARRIVE_AT_NAME_SELECT_BOX), oldArriveAtOptionList);
        selectAnOption(Constants.BookTicket.ARRIVE_AT_NAME_SELECT_BOX, ticket.getArriveStation());

        LogUtils.info("Select " + ticket.getSeatType() + " for 'Seat type'");
        selectAnOption(Constants.BookTicket.SEAT_TYPE_NAME_SELECT_BOX, ticket.getSeatType());

        LogUtils.info("Select " + ticket.getTicketAmount() + " for 'Ticket amount'");
        selectAnOption(Constants.BookTicket.TICKET_AMOUNT_NAME_SELECT_BOX, ticket.getTicketAmount());

        LogUtils.info("Click on 'Book ticket' button");
        clickOnElement(bookTicketButton);
    }
}
