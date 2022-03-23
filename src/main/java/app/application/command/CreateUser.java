package app.application.command;

import shared.domain.bus.Command;

public record CreateUser(String userId, String email) implements Command {
}
