package app.domain;

import shared.domain.AggregateHistory;
import shared.domain.EventSourceAggregateRoot;

final public class User extends EventSourceAggregateRoot {
    private UserId id;
    private Email email;

    private User() {
        super();
    }

    public User(AggregateHistory history) {
        this.fromHistory(history);
    }

    public static User create(UserId id, Email email) {
        User user = new User();
        user.recordThat(new UserWasCreated(id, email));

        return user;
    }

    public UserId id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public void updateEmail(Email email) {
        this.recordThat(new UserEmailWasUpdated(this.id, email));
    }

    void apply(UserWasCreated event) {
        this.id = event.id();
        this.email = event.email();
    }

    void apply(UserEmailWasUpdated event) {
        this.id = event.id();
        this.email = event.email();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return obj == this || (((User) obj).id.equals(this.id) && ((User) obj).email.equals(this.email));
    }
}
