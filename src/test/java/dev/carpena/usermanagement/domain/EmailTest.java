package dev.carpena.usermanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTest {
    @Test
    void it_should_check_for_valid_mails() throws Throwable {
        String validEmail = "felix.carpena@sezamo.es";
        Email email = new Email(validEmail);
        Email expectedEmail = new Email(validEmail);

        assertEquals(expectedEmail, email);
    }

    @Test
    void it_should_fails_on_wrong_email() {
        Assertions.assertThrows(InvalidMailException.class, () -> {
            new Email("someinvalid.email");
        });
    }
}