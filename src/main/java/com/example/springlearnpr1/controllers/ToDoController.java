package com.example.springlearnpr1.controllers;

import com.example.springlearnpr1.models.ToDoTask;
import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.repositories.ToDoTaskRepository;
import com.example.springlearnpr1.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/setCompleted",method = RequestMethod.POST)
    public ResponseEntity<String> setCompleted(@RequestBody Map<String,Long> task, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        ToDoTask toDoTask = toDoTaskRepository.findByIdAndUser(task.get("taskId"),user).orElse(null);
        if(toDoTask == null){
            return ResponseEntity.ok("{success:false}");
        }
        toDoTask.setIsCompleted(true);
        toDoTaskRepository.save(toDoTask);
        return ResponseEntity.ok("{success:true}");
    }
}
