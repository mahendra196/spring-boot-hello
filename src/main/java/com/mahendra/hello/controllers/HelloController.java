package com.mahendra.hello.controllers;

import com.mahendra.hello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/greet")
    public String greet(){
        return "Hello Man!!!!"+userRepository.findById(1).get().getName();
    }
}
