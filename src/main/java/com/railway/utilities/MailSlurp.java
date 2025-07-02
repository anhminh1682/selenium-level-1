package com.railway.utilities;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.*;
import com.railway.constant.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSlurp {
    private InboxDto inbox;
    private ApiClient client;
    private WaitForControllerApi waitForControllerApi;

    public MailSlurp() {
        client = Configuration.getDefaultApiClient();
        client.setConnectTimeout(30000);
        client.setReadTimeout(30000);
        client.setWriteTimeout(30000);

        client.setApiKey(Constants.MailSlurp.API_KEY);

        waitForControllerApi = new WaitForControllerApi(client);
    }

    public void createEmailInbox() throws ApiException {
        // create an inbox
        InboxControllerApi inboxControllerApi = new InboxControllerApi(client);
        inbox = inboxControllerApi.createInbox().useShortAddress(true).execute();
    }

    public String getEmailAddressCreated() {
        return getInboxDto().getEmailAddress();
    }

    public InboxDto getInboxDto() {
        return inbox;
    }

    public String receiveEmail(String subjectContains) throws ApiException {
        MatchOption matchOptionItem = new MatchOption()
                .field(MatchOption.FieldEnum.SUBJECT)
                .should(MatchOption.ShouldEnum.CONTAIN)
                .value(subjectContains);

        MatchOptions matchOptions = new MatchOptions().addMatchesItem(matchOptionItem);

        Email email = waitForControllerApi.waitForMatchingFirstEmail(getInboxDto().getId(), matchOptions).timeout(12000L).unreadOnly(true).execute();
        return email.getBody();
    }

    public String getLinkInEmail(String body) {
        Pattern pattern = Pattern.compile(Constants.MailSlurp.REGEX_GET_LINK_FROM_EMAIL);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
