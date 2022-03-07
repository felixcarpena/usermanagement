package app.domain;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(UserId userId) {
        super(String.format("User %s not found", userId));
    }
}
