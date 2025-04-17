package com.FSDProject.FSD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    
    @GetMapping("/")
    public String home() {
        return "Backend is running!";
    }
}
