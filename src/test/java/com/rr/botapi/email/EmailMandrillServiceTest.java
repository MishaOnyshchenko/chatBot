package com.rr.botapi.email;

import com.rr.botapi.config.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailMandrillServiceTest extends AbstractIntegrationTest {

    @Autowired
    private EmailMandrillService emailMandrillService;

//    @Test
    void shouldSendEmail() {
        //GIVEN
        List<String> emails = List.of("misha.onyshchenko@gmail.com");
        //WHEN
        boolean result = emailMandrillService.sendEmail(emails);
        //THEN
        assertTrue(result);
    }
}