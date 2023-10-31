package com.example.springlearnpr1.controllers;

import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.models.UserRegistrationDTO;
import com.example.springlearnpr1.repositories.RoleRepository;
import com.example.springlearnpr1.repositories.UserRepository;
import com.example.springlearnpr1.utils.RegistrationValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RegistrationValidator registrationValidator;
    public AuthenticationController(UserRepository userRepository, RegistrationValidator registrationValidator, RoleRepository roleRepository) {
        this.registrationValidator = registrationValidator;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "registration";
    }
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult){
        registrationValidator.validate(userRegistrationDTO,bindingResult);
        if(bindingResult.hasErrors()){
            return "/registration";
        }
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setAuthority(roleRepository.findByAuthorityName("ROLE_USER"));
        userRepository.save((User)user);
        return "redirect:/hello";
    }
}

