package com.example.springlearnpr1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springlearnpr1.repositories.UserRepository;
@Controller
@RestController
public class HelloContoller {
    private final UserRepository userRepository;
    @Autowired
    public HelloContoller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String helloPage(){
        return "Hello!" + " " +userRepository.findById(1L).get().getUsername().toString();
    }
}
