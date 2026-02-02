package com.example.demo.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class TestController {
    
    @GetMapping("/student/hello")
    public String sayHello() {
        return "Hello from Student Service!";
    }
}