package com.example.security.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {


    @RequestMapping("/home")
    public String home(Principal principal){
        return " Congradulation!! Now yo have access the  API   " + principal.getName();
    }
}
