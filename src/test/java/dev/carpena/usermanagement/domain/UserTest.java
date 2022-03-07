package dev.carpena.usermanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void it_should_create_a_user() throws Throwable {
        UserId id = new UserId(UUID.randomUUID());
        Email email = new Email("super.fancy@email.cz");
        User user = new User(id, email);

        assertInstanceOf(User.class, user);
        assertEquals(id, user.id());
        assertEquals(email, user.email());
    }
}