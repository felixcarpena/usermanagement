package app.infrastructure.eventstore;

import app.domain.User;
import app.domain.UserId;
import app.domain.UserNotFoundException;
import app.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import shared.domain.AggregateHistory;
import shared.domain.EventStore;

@Service
@Primary
final public class EventStoreUserRepository implements UserRepository {
    private final EventStore store;

    @Autowired
    public EventStoreUserRepository(EventStore store) {
        this.store = store;
    }

    @Override
    public User get(UserId userId) throws UserNotFoundException {
        AggregateHistory history = this.store.load(userId);

        return new User(history);
    }

    @Override
    public void save(User user) {
        this.store.add(user.events());
    }
}
