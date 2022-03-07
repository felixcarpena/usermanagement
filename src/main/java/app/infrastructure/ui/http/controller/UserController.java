package app.infrastructure.ui.http.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final Logger logger;

    @Autowired
    public UserController(Logger logger) {
        this.logger = logger;
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody com.fasterxml.jackson.databind.JsonNode payload) {
        this.logger.info(payload.toPrettyString());
        return new ResponseEntity<>(payload.toPrettyString(), HttpStatus.CREATED);
    }

}