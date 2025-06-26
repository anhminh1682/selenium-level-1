package com.railway.utilities;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.CreateInboxDto;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSlurp {
    private static InboxDto inbox;

    public static void createEmailInbox() throws ApiException {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setApiKey("03cb16e7a290b8f1606c32e65039b57d0038f6b1d88f48ea6808bce4070baaeb");

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

        Email email = waitForControllerApi.waitForLatestEmail().inboxId(getInboxDto().getId()).timeout(120000L).unreadOnly(true).execute();
        return email.getBody();
    }

    public static String getResetPasswordLinkInEmail(String body) {
        Pattern pattern = Pattern.compile("https?://[^\"\\s]*(confirmationCode|resetToken)=[^\"\\s]+");
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
