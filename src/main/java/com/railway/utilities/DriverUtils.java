package com.railway.utilities;

import com.railway.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class DriverUtils {
    public static void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, element.getRect().y)
                .perform();
    }

    public static void waitForElement(int duration, By elementBy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public static void waitForDynamicElement(int duration, String elementXpath, List<WebElement> oldList) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(duration));

        try {
            wait.until(d -> {
                List<WebElement> newList = DriverManager.getDriver().findElements(By.xpath(elementXpath));
                return !newList.equals(oldList);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void waitAlert(int duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void fluentWaitForElement(By elementBy) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        try {
            wait.until(ExpectedConditions.visibilityOf(getWebElement(elementBy)));
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for element");
        }
    }

    public static void fluentWaitForDynamicElement(By elementBy, String currentElementId) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        try {
            wait.until(d -> {
                RemoteWebElement remoteWebElement = (RemoteWebElement) getWebElement(elementBy);
                String newElementId = remoteWebElement.getId();
                System.out.println("Old Element Id: " + currentElementId);
                System.out.println("New Element Id: " + newElementId);
                return !currentElementId.equals(newElementId);
            });
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for element");
        }
    }

    public static WebElement getWebElement(By elementBy) {
        return DriverManager.getDriver().findElement(elementBy);
    }

    public static String getElementText(By elementBy) {
        fluentWaitForElement(elementBy);
        return getWebElement(elementBy).getText().trim();
    }

    public static String getValueOfButton(By elementBy) {
        fluentWaitForElement(elementBy);
        scrollToElement(getWebElement(elementBy));
        return getWebElement(elementBy).getAttribute("value");
    }

    public static void clickOnElement(By elementBy) {
        fluentWaitForElement(elementBy);
        WebElement element = DriverManager.getDriver().findElement(elementBy);
        scrollToElement(element);
        element.click();
    }

    public static void sendKeyElement(By elementBy, CharSequence charSequence) {
        fluentWaitForElement(elementBy);
        WebElement element = getWebElement(elementBy);
        scrollToElement(element);
        element.clear();
        element.sendKeys(charSequence);
    }

    public static void selectElementByVisibleText(By elementBy, String text) {
        fluentWaitForElement(elementBy);
        WebElement element = getWebElement(elementBy);
        Select select = new Select(element);
        scrollToElement(element);
        select.selectByVisibleText(text);
    }
}
