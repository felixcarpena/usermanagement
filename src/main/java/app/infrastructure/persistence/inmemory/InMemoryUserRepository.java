package app.infrastructure.persistence.inmemory;

import app.domain.User;
import app.domain.UserId;
import app.domain.UserNotFoundException;
import app.domain.UserRepository;

import java.util.HashSet;

public class InMemoryUserRepository implements UserRepository {
    private final HashSet<User> users = new HashSet<>();

    @Override
    public User get(UserId userId) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.id().equals(userId))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public void save(User user) {
        this.users.add(user);
    }
}
