package com.tests.ultilities.MailHelpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.railway.constant.Constants;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class mailDataFromJSON {
    private static String filePath = Constants.URL.MAIL_DATA_PATH;

    public static Map<String, String> mailData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(filePath));

            JsonNode mailData = root.path("mailData");
            String inboxId = mailData.path("inboxId").asText(null);
            String emailAddress = mailData.path("emailAddress").asText(null);

            Map<String, String> result = new HashMap<>();
            result.put("inboxId", inboxId);
            result.put("emailAddress", emailAddress);

            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void writeDataToJsonFile(String inboxId, String email) {
        try {
            File file = new File(filePath);
            ObjectMapper mapper = new ObjectMapper();

            if (!file.exists()) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }

            JsonNode root = mapper.readTree(file);
            if (!root.isObject()) {
                throw new IllegalArgumentException("File does not contain a JSON object: " + filePath);
            }

            ObjectNode rootObj = (ObjectNode) root;
            JsonNode mailDataNode = rootObj.get("mailData");
            if (mailDataNode == null || !mailDataNode.isObject()) {
                throw new IllegalArgumentException("Field 'mailData' is missing or not an object");
            }

            ObjectNode mailDataObj = (ObjectNode) mailDataNode;

            mailDataObj.put("inboxId", inboxId);
            mailDataObj.put("emailAddress", email);

            mapper.writeValue(file, rootObj);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
