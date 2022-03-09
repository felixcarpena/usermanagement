package app.application.command;

import app.config.Subscriber;
import app.domain.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shared.domain.bus.Bus;
import shared.domain.bus.Handler;
import shared.domain.bus.Message;
import shared.domain.bus.Response;

import java.util.UUID;

@Service
@Subscriber
public class CreateUserHandler implements Handler<CreateUser> {
    private final UserRepository userRepository;
    private final Bus bus;
    private final Logger logger;

    @Autowired
    public CreateUserHandler(UserRepository userRepository, Bus bus, Logger logger) {
        this.userRepository = userRepository;
        this.bus = bus;
        this.logger = logger;
    }

    @Override
    public Class<? extends Message> manage() {
        return CreateUser.class;
    }

    @Override
    public Response handle(CreateUser command) {
        try {
            UserId userId = new UserId(UUID.fromString(command.userId()));
            Email email = new Email(command.email());
            //ensure does not exists
            this.userRepository.get(userId);
            User user = User.create(userId, email);
            this.userRepository.save(user);
            user.events().forEach(this.bus::dispatch);
        } catch (UserNotFoundException | InvalidMailException e) {
            //for the moment do nothing
            this.logger.error(e.getMessage());
        }
        return null;
    }
}
