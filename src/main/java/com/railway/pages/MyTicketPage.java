package com.railway.pages;

import com.railway.driver.DriverManager;
import com.railway.utilities.DriverUtils;
import com.railway.utilities.LogUtils;
import com.railway.utilities.Ticket;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.railway.utilities.DriverUtils.clickOnElement;

public class MyTicketPage extends BasePage {
    private final String rowOfTicketXpath = "//table[contains(@class, 'MyTable')]//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s']]//input[@value='Cancel']";

    private List<WebElement> getListRowOfTicket(Ticket ticket) {
        return DriverManager.getDriver().findElements(
                By.xpath(
                        String.format(
                                rowOfTicketXpath,
                                ticket.getDepartStation(),
                                ticket.getArriveStation(),
                                ticket.getSeatType(),
                                ticket.getDepartDate(),
                                ticket.getTicketAmount()
                        )
                )
        );
    }

    public int countOfRowTicket(Ticket ticket) {
        return getListRowOfTicket(ticket).size();
    }

    public void cancelATicket(Ticket ticket) {
        LogUtils.info("Click on 'Cancel' button of ticket which user want to cancel.");
        By elementBy = By.xpath(String.format(
                rowOfTicketXpath,
                ticket.getDepartStation(),
                ticket.getArriveStation(),
                ticket.getSeatType(),
                ticket.getDepartDate(),
                ticket.getTicketAmount()
        ));

        clickOnElement(elementBy);

        // Click pop up
        LogUtils.info("Click on 'OK' button on Confirmation message 'Are you sure?'");
        DriverUtils.waitAlert(10);
        Alert alert = DriverManager.getDriver().switchTo().alert();

        alert.accept();
    }
}
