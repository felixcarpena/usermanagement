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
public class UpdateUserEmailHandler implements Handler<UpdateUserEmail> {
    private final UserRepository userRepository;
    private final Bus bus;
    private final Logger logger;

    @Autowired
    public UpdateUserEmailHandler(UserRepository userRepository, Bus bus, Logger logger) {
        this.userRepository = userRepository;
        this.bus = bus;
        this.logger = logger;
    }

    @Override
    public Class<? extends Message> manage() {
        return UpdateUserEmail.class;
    }

    @Override
    public Response handle(UpdateUserEmail command) {
        try {
            UserId userId = new UserId(UUID.fromString(command.userId()));
            Email email = new Email(command.email());
            User user = this.userRepository.get(userId);
            user.updateEmail(email);
            this.userRepository.save(user);
            user.events().forEach(this.bus::dispatch);
        } catch (InvalidMailException | UserNotFoundException e) {
            //for the moment do nothing
            this.logger.error(e.getMessage());
        }
        return null;
    }
}
