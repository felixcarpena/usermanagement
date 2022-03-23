package app.application.command;

import shared.domain.bus.Command;

public record UpdateUserEmail(String userId, String email) implements Command {
}
