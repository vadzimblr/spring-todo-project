package com.example.springlearnpr1.controllers;

import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.models.UserRegistrationDTO;
import com.example.springlearnpr1.repositories.RoleRepository;
import com.example.springlearnpr1.repositories.UserRepository;
import com.example.springlearnpr1.utils.RegistrationValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RegistrationValidator registrationValidator;
    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();
    private final AuthenticationManager authenticationManager;
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    public AuthenticationController(UserRepository userRepository, RegistrationValidator registrationValidator,
                                    RoleRepository roleRepository, AuthenticationManager authenticationManager
                                    ) {
        this.registrationValidator = registrationValidator;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "registration";
    }
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult){
        registrationValidator.validate(userRegistrationDTO,bindingResult);
        if(bindingResult.hasErrors()){
            return "/registration";
        }
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setAuthority(roleRepository.findByAuthorityName("ROLE_USER"));
        userRepository.save(user);
        if(userRepository.existsByUsername(user.getUsername())){
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),user.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context,request,response);
        }
        return "redirect:/hello";
    }
}

