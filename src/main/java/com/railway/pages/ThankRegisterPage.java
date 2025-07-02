package com.railway.pages;

import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.getElementText;

public class ThankRegisterPage extends BasePage {
    private final By registerValidInfoHeading = By.xpath("//h1[@align='center']");

    public String getRegisterValidInforHeading() {
        return getElementText(registerValidInfoHeading);
    }
}
