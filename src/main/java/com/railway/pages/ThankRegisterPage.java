package com.railway.pages;

import org.openqa.selenium.By;

import static com.railway.utilities.DriverUtils.getElementText;
import static com.railway.utilities.DriverUtils.webElement;

public class ThankRegisterPage extends BasePage {
    private final By registerValidInfoHeading = By.xpath("//h1[@align='center']");

    public String getRegisterValidInforHeading() {
        return getElementText(webElement(registerValidInfoHeading));
    }
}
