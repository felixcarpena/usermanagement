package app.application.query;

import shared.domain.bus.Query;

public class UserOfId implements Query {
    private final String id;

    public UserOfId(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
