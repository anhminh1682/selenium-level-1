package com.railway.pages;

import com.railway.utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.railway.utilities.DriverUtils.clickOnElement;
import static com.railway.utilities.DriverUtils.webElement;

public class TimetablePage extends BasePage{
    private final String bookTicketXpath = "//table[contains(@class, 'MyTable')]//tr//td[text()='%s']//following-sibling::td[text()='%s']//following-sibling::td/a[text()='book ticket']";

    public void clickOnBookTicketButton(String departFrom, String arriveAt) {
        clickOnElement(By.xpath(String.format(bookTicketXpath, departFrom, arriveAt)));
    }
}
