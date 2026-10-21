package org.example.splab.controllers;

import org.example.splab.difexample.ClientComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final ClientComponent clientComponent;

    // Injectăm ClientComponent în controller[cite: 26]
    @Autowired
    public HelloController(ClientComponent clientComponent) {
        this.clientComponent = clientComponent;
    }

    @GetMapping("/")
    public String hello() {
        // Returnăm mesajul cerut[cite: 26]
        return "Hello from Spring Boot <br> Hello from ClientComponent = " + clientComponent.toString();
    }
}