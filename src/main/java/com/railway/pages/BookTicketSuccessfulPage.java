package com.railway.pages;

import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.getElementText;

public class BookTicketSuccessfulPage extends BasePage{
    private final By bookSuccessHeadingMessage = By.xpath("//h1[@align='center']");
    private final String ticketValueInAColumn = "//table[contains(@class, 'MyTable')]//td[count(//table[contains(@class, 'MyTable')]//th[text()='%s']/preceding-sibling::th) + 1]";

    public String getBookSuccessHeadingMessage() {
        return getElementText(bookSuccessHeadingMessage);
    }

    public String getTicketValueInAColumn(String headingColumn) {
        return getElementText(By.xpath(String.format(ticketValueInAColumn, headingColumn)));
    }
}
