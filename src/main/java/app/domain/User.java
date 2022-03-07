package app.domain;

final public class User {
    final private UserId id;
    final private Email email;

    public User(UserId id, Email email) {
        this.id = id;
        this.email = email;
    }

    public UserId id() {
        return id;
    }

    public Email email() {
        return email;
    }
}
