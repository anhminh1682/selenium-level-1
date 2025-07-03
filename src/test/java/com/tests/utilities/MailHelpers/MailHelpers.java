package com.tests.utilities.MailHelpers;

import com.railway.constant.Constants;
import com.railway.utilities.Helpers;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class MailHelpers {
    private String apiKey;

    public MailHelpers() {
        Dotenv dotenv = Dotenv.load();
        apiKey = dotenv.get("API_MAIL_SLURP_KEY");
    }

    private RequestSpecification getRequestSpecification() {
        return  given()
                .baseUri(Constants.MailSlurp.URI)
                .header("accept", "*/*")
                .header("x-api-key", apiKey)
                .urlEncodingEnabled(true);
    }

    public String callToGetEmailInfor(String jsonPath, String subject) {
        RequestSpecification request = getRequestSpecification()
                .basePath("emails")
                .queryParam("page", 0)
                .queryParam("size", 20)
                .queryParam("sort", "ASC")
                .queryParam("unreadOnly", true)
                .queryParam("searchFilter", subject);

        long startTime = System.currentTimeMillis();
        long duration = 5000;
        long currentTime = startTime;

        Response response;

        do {
            response = request.get();
            currentTime += 500;
        } while (response.jsonPath().getList(jsonPath).isEmpty() && currentTime < startTime + duration);

        return response.jsonPath().getList(jsonPath).get(0).toString();
    }

    public String callToGetEmailBody(String emailId) {
        RequestSpecification request = getRequestSpecification()
                .basePath(String.format("emails/%s/body", emailId));

        long startTime = System.currentTimeMillis();
        long duration = 5000;
        long currentTime = startTime;

        Response response;
        do {
            response = request.get();
            currentTime += 500;
        } while (Objects.equals(response.getBody().print(), null) && currentTime < startTime + duration);

        return response.getBody().print();
    }

    public String getLinkInEmailByAPI(String subject) {
        String emailId = callToGetEmailInfor("content.id", subject);
        String body = callToGetEmailBody(emailId);

        return Helpers.getLinkInEmail(body);
    }

    public boolean callToCheckInboxIdExpired(String inboxId) {
        RequestSpecification request = getRequestSpecification()
                .basePath(String.format("expired/inbox/%s", inboxId));

        Response response = request.get();
        return response.statusCode() == 200;
    }

    public boolean callToDeleteAllInboxes() {
        RequestSpecification request = getRequestSpecification()
                .basePath("inboxes");

        Response response = request.delete();
        return response.statusCode() == 200;
    }

    public String[] callToCreateInbox() {
        RequestSpecification request = getRequestSpecification()
                .basePath("inboxes")
                .queryParam("useShortAddress", true);

        Response response = request.post();

        if(response.statusCode() == 201) {
            String emailAddress = response.jsonPath().getString("emailAddress");
            String inboxId = response.jsonPath().getString("id");
            return new String[]{emailAddress, inboxId};
        }

        return null;
    }
}
