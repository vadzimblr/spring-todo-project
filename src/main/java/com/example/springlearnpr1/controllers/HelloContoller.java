package com.example.springlearnpr1.controllers;


import com.example.springlearnpr1.repositories.ToDoTaskRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springlearnpr1.repositories.UserRepository;


import java.security.Principal;

//@Controller
@RestController
public class HelloContoller {
    private final UserRepository userRepository;
    private final ToDoTaskRepository toDoTaskRepository;
    private HttpServletRequest request;

    @Autowired
    public HelloContoller(UserRepository userRepository, HttpServletRequest request,ToDoTaskRepository toDoTaskRepository) {
        this.userRepository = userRepository;
        this.request = request;
        this.toDoTaskRepository = toDoTaskRepository;
    }

    @GetMapping("/hello")
    public String helloPage(Principal principal){

        return "Hello, " + principal.getName() + " " + userRepository.findByUsername(principal.getName()).get();

    }

}
