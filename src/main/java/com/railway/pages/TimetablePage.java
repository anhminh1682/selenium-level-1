package com.railway.pages;

import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.clickOnElement;

public class TimetablePage extends BasePage{
    private final String bookTicketXpath = "//table[contains(@class, 'MyTable')]//tr//td[text()='%s']//following-sibling::td[text()='%s']//following-sibling::td/a[text()='book ticket']";

    public void clickOnBookTicketButton(String departFrom, String arriveAt) {
        clickOnElement(By.xpath(String.format(bookTicketXpath, departFrom, arriveAt)));
    }
}
