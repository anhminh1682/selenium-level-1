package com.railway.pages;

import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.Helpers;
import com.railway.utilities.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BookTicket extends BasePage {
    private final String dateSelectBox = "//select[@name='%s']";

    private final By bookTicketButton = By.xpath("//input[@value='Book ticket']");

    private WebElement getSelectBoxByName(String name) {
        return DriverManager.getDriver().findElement(By.xpath(String.format(dateSelectBox, name)));
    }

    public void selectAnOption(String selectBoxName, String option) {
        Select selectBox = new Select(getSelectBoxByName(selectBoxName));
        selectBox.selectByVisibleText(option);
    }

    public void bookATicket(Ticket ticket) {
        selectAnOption("Date", ticket.getDate());
        selectAnOption("DepartStation", ticket.getDepartStation());
        selectAnOption("ArriveStation", ticket.getArriveStation());
        selectAnOption("SeatType", ticket.getSeatType());
        selectAnOption("TicketAmount", ticket.getTicketAmount());

        Helpers.scrollToElement(webElement(bookTicketButton));
        webElement(bookTicketButton).click();
    }
}
