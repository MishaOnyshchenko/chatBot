package com.rr.botapi.email;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmailMandrillService {

    private static final String MANDRILL_KEY = "md-N37ppagOJGVqW4DBJBO12A";

    public boolean sendEmail(List<String> emailsTo, String subject, String textHtml) {
        log.warn("[sendEmail] {} {} {}", emailsTo, subject, textHtml);
        ArrayList<MandrillMessage.Recipient> recipients = getRecipients(emailsTo);
        MandrillMessage message = getMessage(recipients, subject, textHtml);
        return send(message);
    }

    private ArrayList<MandrillMessage.Recipient> getRecipients(List<String> emailsTo) {
        ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<>();
        for (String emailTo : emailsTo) {
            MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
            recipient.setEmail(emailTo);
            recipients.add(recipient);
        }
        return recipients;
    }

    private MandrillMessage getMessage(ArrayList<MandrillMessage.Recipient> recipients, String subject, String textHtml) {
        MandrillMessage message = new MandrillMessage();
        message.setSubject(subject);
        message.setHtml(textHtml);
        message.setAutoText(true);
        message.setFromEmail("info@mihanik.club");
        message.setFromName("info");
        message.setTo(recipients);
        message.setPreserveRecipients(true);
        return message;
    }

    private boolean send(MandrillMessage message) {
        MandrillApi mandrillApi = new MandrillApi(MANDRILL_KEY);
        try {
            MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, false);
            for (MandrillMessageStatus status : messageStatusReports) {
                log.info("[sendEmail] MandrillMessageStatus {} {} {} {}", status.getEmail(), status.getId(), status.getStatus(), status.getRejectReason());
                if (!status.getStatus().equals("sent")) {
                    throw new RuntimeException("Email not sent: " + status.getEmail() + ", status: " + status.getStatus() + ", reason: " + status.getRejectReason());
                }
            }
            return true;
        } catch (MandrillApiError | IOException er) {
            log.error("email send error", er);
            return false;
        }
    }
}
