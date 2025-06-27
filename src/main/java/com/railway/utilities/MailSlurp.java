package com.railway.utilities;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;
import com.railway.constant.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSlurp {
    private static InboxDto inbox;

    public static void createEmailInbox() throws ApiException {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setApiKey(Constants.MailSlurp.API_KEY);

        // create an inbox
        InboxControllerApi inboxControllerApi = new InboxControllerApi(client);
        inbox = inboxControllerApi.createInbox().useShortAddress(true).execute();
    }

    public static String getEmailAddressCreated() {
        return getInboxDto().getEmailAddress();
    }

    public static InboxDto getInboxDto() {
        return inbox;
    }

    public static String receiveEmail() throws ApiException {
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi();

        Email email = waitForControllerApi.waitForLatestEmail().inboxId(getInboxDto().getId()).timeout(240000L).unreadOnly(true).execute();
        return email.getBody();
    }

    public static String getResetPasswordLinkInEmail(String body) {
        Pattern pattern = Pattern.compile(Constants.MailSlurp.REGEX_GET_LINK_FROM_EMAIL);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
