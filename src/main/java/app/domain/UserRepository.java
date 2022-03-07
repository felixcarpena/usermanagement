package app.domain;

public interface UserRepository {
    User get(UserId userId) throws UserNotFoundException;

    void save(User user);
}
