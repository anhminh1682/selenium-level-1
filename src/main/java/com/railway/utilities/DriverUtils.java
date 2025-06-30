package com.railway.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class DriverUtils {
    public static void scrollToElement(WebElement element) {
        new Actions(DriverManager.getDriver())
                .scrollByAmount(0, element.getRect().y)
                .perform();
    }

    public static void waitForElement(int duration, By elementBy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
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
                .withTimeout(Duration.ofSeconds(5000))
                .pollingEvery(Duration.ofSeconds(200))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement(elementBy)));
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for element");
        }
    }

    public static WebElement webElement(By elementBy) {
        return DriverManager.getDriver().findElement(elementBy);
    }

    public static String getElementText(By elementBy) {
        fluentWaitForElement(elementBy);
        return webElement(elementBy).getText().trim();
    }

    public static void clickOnElement(By elementBy) {
        fluentWaitForElement(elementBy);
        WebElement element = DriverManager.getDriver().findElement(elementBy);
        scrollToElement(element);
        element.click();
    }

    public static void sendKeyElement(By elementBy, CharSequence charSequence) {
        fluentWaitForElement(elementBy);
        WebElement element = webElement(elementBy);
        scrollToElement(element);
        element.clear();
        element.sendKeys(charSequence);
    }

    public static void selectElementByVisibleText(By elementBy, String text) {
        fluentWaitForElement(elementBy);
        WebElement element = webElement(elementBy);
        Select select = new Select(element);
        scrollToElement(element);
        select.selectByVisibleText(text);
    }

    private static List<Ticket> saveDatasetIntoTicketList() throws IOException {
        File file = new File("src/main/resources/dataset/tickets.json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
        List<Ticket> tickets = new ArrayList<>();

        for (JsonNode node : rootNode) {
            Ticket ticket = new Ticket();
            ticket.setDepartStation(node.get("DepartStation").asText());
            ticket.setArriveStation(node.get("ArriveStation").asText());
            ticket.setDepartDate(node.get("Date").asText());
            ticket.setSeatType(node.get("SeatType").asText());
            ticket.setTicketAmount(node.get("Amount").asText());
            tickets.add(ticket);
        }

        return tickets;
    }

    public static Ticket getRandomTicket() throws IOException {
        return saveDatasetIntoTicketList().get(new Random().nextInt(saveDatasetIntoTicketList().size()));
    }
}
