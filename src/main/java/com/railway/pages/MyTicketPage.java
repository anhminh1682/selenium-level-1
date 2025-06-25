package com.railway.pages;

import com.railway.driver.DriverManager;

public class MyTicketPage extends BasePage {

    public String getMyTicketPageTitle() {
        return DriverManager.getDriver().getTitle();
    }
}
