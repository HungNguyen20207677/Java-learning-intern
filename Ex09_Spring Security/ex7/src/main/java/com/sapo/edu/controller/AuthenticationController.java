package com.sapo.edu.controller;

import com.sapo.edu.model.AuthenticationResponse;
import com.sapo.edu.model.Users;
import com.sapo.edu.service.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Users request
            ) {
        logger.info("Received registration request: " + request.toString());

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Users request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
