package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import com.railway.utilities.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage extends BasePage {
    private final String selectBoxXpath = "//select[@name='%s']";
    private final String currentOptionOfSelectBoxXpath = "//select[@name='%s']/option[@selected='selected']";

    private final By bookTicketButton = By.xpath("//input[@value='Book ticket']");

    private WebElement getSelectBoxByName(String name) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(selectBoxXpath, name)));
    }

    private WebElement getCurrentOptionOfSelectBoxXpath(String name) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(currentOptionOfSelectBoxXpath, name)));
    }

    public String getCurrentValueOfASelectBox(String name) {
        return getElementText(getCurrentOptionOfSelectBoxXpath(name));
    }

    public void selectAnOption(String selectBoxName, String option) {
        Select selectBox = new Select(getSelectBoxByName(selectBoxName));
        selectBox.selectByVisibleText(option);
    }

    public void bookATicket(Ticket ticket) {
        selectAnOption(Constants.BookTicket.DEPART_DATE_NAME_SELECT_BOX, ticket.getDate());
        selectAnOption(Constants.BookTicket.DEPART_FROM_NAME_SELECT_BOX, ticket.getDepartStation());
        selectAnOption(Constants.BookTicket.ARRIVE_AT_NAME_SELECT_BOX, ticket.getArriveStation());
        selectAnOption(Constants.BookTicket.SEAT_TYPE_NAME_SELECT_BOX, ticket.getSeatType());
        selectAnOption(Constants.BookTicket.TICKET_AMOUNT_NAME_SELECT_BOX, ticket.getTicketAmount());

        Helpers.scrollToElement(webElement(bookTicketButton));
        webElement(bookTicketButton).click();
    }
}
