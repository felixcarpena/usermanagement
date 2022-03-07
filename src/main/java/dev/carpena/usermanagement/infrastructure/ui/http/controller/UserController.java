package dev.carpena.usermanagement.infrastructure.ui.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String index() {
        return "I'm alive!";
    }

}