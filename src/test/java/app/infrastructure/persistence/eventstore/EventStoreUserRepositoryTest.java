package app.infrastructure.persistence.eventstore;

import app.domain.Email;
import app.domain.User;
import app.domain.UserId;
import app.domain.UserRepository;
import app.infrastructure.eventstore.EventStoreUserRepository;
import app.infrastructure.persistence.inmemory.InMemoryEventStore;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventStoreUserRepositoryTest {
    @Test
    public void it_should_store_and_retrieve_an_user() throws Throwable {
        UserRepository userRepository = new EventStoreUserRepository(new InMemoryEventStore());
        UserId id = new UserId(UUID.randomUUID());
        User user = User.create(id, new Email("super@fancy.email"));
        user.updateEmail(new Email("another@fancy.email"));
        userRepository.save(user);

        User foundUser = userRepository.get(id);

        assertEquals(user, foundUser);
    }
}
