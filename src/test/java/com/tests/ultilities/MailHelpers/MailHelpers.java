package com.tests.ultilities;

import com.railway.constant.Constants;
import com.railway.utilities.Helpers;
import com.railway.utilities.LogUtils;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class MailHelpers {
    private static RequestSpecification requestSpecification;

    private static RequestSpecification getRequestSpecification() {
        return  given()
                .baseUri(Constants.MailSlurp.URI)
                .header("accept", "*/*")
                .header("x-api-key", Constants.MailSlurp.API_KEY)
                .urlEncodingEnabled(true);
    }

    public static String callToGetEmailInfor(String jsonPath, String subject) {
        RequestSpecification request = getRequestSpecification()
                .basePath("emails")
                .queryParam("page", 0)
                .queryParam("size", 20)
                .queryParam("sort", "ASC")
                .queryParam("unreadOnly", true)
                .queryParam("searchFilter", subject);

        Response response = request.get();
        return response.jsonPath().getList(jsonPath).get(0).toString();
    }

    public static String callToGetEmailBody(String emailId) {
        RequestSpecification request = getRequestSpecification()
                .basePath(String.format("emails/%s/body", emailId));

        Response response = request.get();
        return response.getBody().print().toString();
    }

    public static String getLinkInEmailByAPI(String subject) {
        String emailId = MailHelpers.callToGetEmailInfor("content.id", subject);
        String body = MailHelpers.callToGetEmailBody(emailId);

        String linkInEmail = "";

        while (linkInEmail.isEmpty()) {
            linkInEmail = Helpers.getLinkInEmail(body);
        }

        return linkInEmail;
    }

    public static boolean callToCheckInboxIdExpired(String inboxId) {
        RequestSpecification request = getRequestSpecification()
                .basePath(String.format("expired/inbox/%s", inboxId));

        Response response = request.get();
        return response.statusCode() == 200;
    }

    public static boolean callToDeleteAllInboxes() {
        RequestSpecification request = getRequestSpecification()
                .basePath("inboxes");

        Response response = request.delete();
        return response.statusCode() == 200;
    }

    public static String callToCreateInbox() {
        RequestSpecification request = getRequestSpecification()
                .basePath("inboxes")
                .queryParam("useShortAddress", true);

        Response response = request.post();
        String emailAddress = "";

        while (emailAddress.isEmpty()) {
            emailAddress = response.jsonPath().getString("emailAddress");
        }

        return emailAddress;
    }

    public static void main(String[] args) {
//        String body = callToGetEmailBody(emailId);
//
//        String linkInEmail = Helpers.getLinkInEmail(body);
//        callToCheckInboxIdExpired("82b3c89e-310d-48db-bef4-c6ae41d1a5a7");
    }
}
