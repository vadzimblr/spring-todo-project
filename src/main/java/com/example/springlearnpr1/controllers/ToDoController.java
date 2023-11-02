package com.example.springlearnpr1.controllers;

import com.example.springlearnpr1.models.ToDoTask;
import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.repositories.ToDoTaskRepository;
import com.example.springlearnpr1.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    private final UserRepository userRepository;
    private final ToDoTaskRepository toDoTaskRepository;

    public ToDoController(UserRepository userRepository, ToDoTaskRepository toDoTaskRepository) {
        this.userRepository = userRepository;
        this.toDoTaskRepository = toDoTaskRepository;
    }

    @GetMapping
    public ModelAndView showTasks(Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        List<ToDoTask> taskList = user.getTasks();
        ModelAndView MVObject = new ModelAndView("todo");
        MVObject.addObject("taskList",taskList);
        return MVObject;
    }
}
