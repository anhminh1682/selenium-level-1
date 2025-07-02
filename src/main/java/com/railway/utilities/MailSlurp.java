package com.railway.utilities;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;
import com.mailslurp.models.MatchOption;
import com.mailslurp.models.MatchOptions;
import io.github.cdimascio.dotenv.Dotenv;

public class MailSlurp {
    private InboxDto inbox;
    private ApiClient client;
    private WaitForControllerApi waitForControllerApi;
    private static final Dotenv dotenv = Dotenv.load();
    private static final String apiKey = dotenv.get("API_MAIL_SLURP_KEY");

    public MailSlurp() {
        client = Configuration.getDefaultApiClient();
        client.setConnectTimeout(30000);
        client.setReadTimeout(30000);
        client.setWriteTimeout(30000);

        client.setApiKey(apiKey);

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
}
