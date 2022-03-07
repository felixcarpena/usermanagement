package app.infrastructure.persistence.inmemory;

import app.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryUserRepositoryTest {
    private final InMemoryUserRepository sut = new InMemoryUserRepository();

    @Test
    void it_should_persists_and_return_and_user() throws Throwable {
        UserId id = new UserId(UUID.randomUUID());
        User user = new User(id, new Email("fancy@email.com"));
        sut.save(user);

        assertEquals(user, sut.get(id));
    }

    @Test
    void it_should_fails_if_user_not_found() throws Throwable {
        UserId id = new UserId(UUID.randomUUID());
        UserId anotherId = new UserId(UUID.randomUUID());
        User user = new User(id, new Email("fancy@email.com"));
        sut.save(user);

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            sut.get(anotherId);
        });
    }
}