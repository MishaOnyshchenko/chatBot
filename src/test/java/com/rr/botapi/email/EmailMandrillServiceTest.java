package com.rr.botapi.email;

import com.rr.botapi.config.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailMandrillServiceTest extends AbstractIntegrationTest {

    @Autowired
    private EmailMandrillService emailMandrillService;

    //    @Test
    void shouldSendEmail() {
        //GIVEN
        List<String> emails = List.of("info@mihanik.club");
        String subject = "info club";
        String textHtml = "<h1>Hi pal!</h1><br/>Really, I'm just saying hi!";
        //WHEN
        boolean result = emailMandrillService.sendEmail(emails, subject, textHtml);
        //THEN
        assertTrue(result);
    }
}