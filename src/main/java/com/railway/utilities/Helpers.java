package com.railway.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.constant.Constants;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

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

    public static String getLinkInEmail(String body) {
        Pattern pattern = Pattern.compile(Constants.MailSlurp.REGEX_GET_LINK_FROM_EMAIL);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public static String randomBase62(int length) {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return sb.toString();
    }
}
