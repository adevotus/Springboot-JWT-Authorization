package com.example.security.controller;


import com.example.security.request.LoginRequest;
import com.example.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/toke_V1")
    public String token(Authentication auth) {

        LOG.info("token requested for the user: {}" + auth.getName());
        String token = tokenService.generateToken(auth);
        LOG.info("token granted " + token);

        return token;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) {
     Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
       return tokenService.generateToken(authentication);
    }
}
