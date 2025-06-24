package com.example.pages;

import com.example.constant.Constants;
import com.example.driver.DriverManager;

import java.util.Objects;

public class MyTicketPage {

    public boolean checkMyTicketPageDisplayed() {
        return Objects.equals(DriverManager.getDriver().getTitle(), Constants.myTicketPageTitle);
    }
}
