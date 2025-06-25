package com.railway.pages;

import com.railway.utilities.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimetablePage extends BasePage{
    private final String bookTicketXpath = "//table[contains(@class, 'MyTable')]//tr//td[text()='%s']//following-sibling::td[text()='%s']//following-sibling::td/a[text()='book ticket']";

    public void clickOnBookTicketButton(String departFrom, String arriveAt) {
        WebElement element = webElement(By.xpath(String.format(bookTicketXpath, departFrom, arriveAt)));
        Helpers.scrollToElement(element);
        element.click();
    }
}
