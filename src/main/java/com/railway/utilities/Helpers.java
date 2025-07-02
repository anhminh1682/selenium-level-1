package com.railway.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helpers {
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
