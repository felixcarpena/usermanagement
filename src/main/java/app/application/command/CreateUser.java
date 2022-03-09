package app.application.command;

import shared.domain.bus.Command;

public class CreateUser implements Command {
    private final String userId;
    private final String email;

    public CreateUser(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String userId() {
        return userId;
    }

    public String email() {
        return email;
    }
}
