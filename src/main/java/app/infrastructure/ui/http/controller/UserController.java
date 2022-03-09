package app.infrastructure.ui.http.controller;

import app.domain.Email;
import app.domain.User;
import app.domain.UserId;
import app.domain.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {
    private final Logger logger;
    private final UserRepository userRepository;

    @Autowired
    public UserController(Logger logger, UserRepository userRepository) {
        this.logger = logger;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody com.fasterxml.jackson.databind.JsonNode payload) throws Throwable {
        this.logger.info(payload.toPrettyString());
        this.userRepository.save(User.create(new UserId(UUID.randomUUID()), new Email("any@email.com")));
        return new ResponseEntity<>(payload.toPrettyString(), HttpStatus.CREATED);
    }

}