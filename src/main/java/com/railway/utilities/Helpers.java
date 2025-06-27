package com.railway.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helpers {
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
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(duration)).ignoring(Exception.class);

        wait.until(d -> {
            List<WebElement> newList = DriverManager.getDriver().findElements(By.xpath(elementXpath));
            return !newList.equals(oldList);
        });
    }

    public static void waitAlert(int duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.alertIsPresent());
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
