package app.infrastructure.ui.http.controller;

import app.application.command.CreateUser;
import app.application.command.UpdateUserEmail;
import app.application.query.UserOfId;
import app.application.query.UserOfIdResponse;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shared.domain.bus.Bus;
import shared.domain.bus.Response;

import java.util.Optional;

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
    public ResponseEntity create(@RequestBody com.fasterxml.jackson.databind.JsonNode payload) {
        String userId = payload.get("data").get("id").textValue();
        String email = payload.get("data").get("email").textValue();
        this.bus.dispatch(new CreateUser(userId, email));
        Optional<Response> response = this.bus.dispatch(new UserOfId(userId));
        return response.map(
                (userOfIdResponse) -> new ResponseEntity<>((UserOfIdResponse) userOfIdResponse, HttpStatus.CREATED)
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @PatchMapping(path = "/user/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity update(@PathVariable @NotNull String userId, @RequestBody com.fasterxml.jackson.databind.JsonNode payload) {
        String email = payload.get("data").get("email").textValue();
        this.bus.dispatch(new UpdateUserEmail(userId, email));
        Optional<Response> response = this.bus.dispatch(new UserOfId(userId));
        return response.map(
                (userOfIdResponse) -> new ResponseEntity<>((UserOfIdResponse) userOfIdResponse, HttpStatus.OK)
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @GetMapping(path = "/user/{userId}", produces = "application/json")
    public ResponseEntity info(@PathVariable @NotNull String userId) {
        Optional<Response> response = this.bus.dispatch(new UserOfId(userId));
        return response.map(
                (userOfIdResponse) -> new ResponseEntity<>((UserOfIdResponse) userOfIdResponse, HttpStatus.OK)
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }
}