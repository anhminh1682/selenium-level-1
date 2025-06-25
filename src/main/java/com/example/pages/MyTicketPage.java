package com.example.pages;

import com.example.driver.DriverManager;

public class MyTicketPage extends BasePage {

    public String getMyTicketPageTitle() {
        return DriverManager.getDriver().getTitle();
    }
}
