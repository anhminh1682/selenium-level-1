package com.railway.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ticket {
    private String departDate;
    private String departStation;
    private String arriveStation;
    private String seatType;
    private String ticketAmount;

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public void setDepartStation(String departStation) {
        this.departStation = departStation;
    }

    public void setArriveStation(String arriveStation) {
        this.arriveStation = arriveStation;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public Ticket() {
    }

    public Ticket(String date, String departStation, String arriveStation, String seatType, String ticketAmount) {
        this.departDate = date;
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public String getDepartDate() {
        return departDate;
    }

    public String getDepartStation() {
        return departStation;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getTicketAmount() {
        return ticketAmount;
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

    public static void main(String[] args) throws IOException {
        Ticket ticket = new Ticket();

        System.out.println(ticket.saveDatasetIntoTicketList().size());
    }
}
