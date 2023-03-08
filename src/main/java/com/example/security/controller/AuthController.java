package com.example.security.controller;


import com.example.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/token")
    public String token(Authentication auth) {

        LOG.info("token requested for the user: {}" + auth.getName());
        String token = tokenService.generateToken(auth);
        LOG.info("token granted " + token);

        return token;
    }
}
