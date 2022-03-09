package app.infrastructure.ui.http.controller;

import app.application.command.CreateUser;
import app.application.query.UserOfId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shared.domain.bus.Bus;
import shared.domain.bus.Response;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final Logger logger;
    private final Bus bus;

    @Autowired
    public UserController(Logger logger, Bus bus) {
        this.logger = logger;
        this.bus = bus;
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody com.fasterxml.jackson.databind.JsonNode payload) throws Throwable {
        this.logger.info(payload.toPrettyString());
        String userId = UUID.randomUUID().toString();
        this.bus.dispatch(new CreateUser(userId, "any@email.com"));
        Optional<Response> response = this.bus.dispatch(new UserOfId(userId));
        //payload.toPrettyString()
        return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);
    }

}