package com.sapo.edu.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Value("${user.username}")
    private String userName;

    @GetMapping("/username")
    public String getUserName() {
        return userName;
    }
}
