package dev.carpena.usermanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {
    @Test
    void id_lifecycle() {
        UUID uuid = UUID.randomUUID();
        UserId userId = new UserId(uuid);

        UserId expected = new UserId(uuid);

        assertEquals(expected, userId);
    }
}