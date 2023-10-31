package com.example.springlearnpr1.utils;


import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.models.UserRegistrationDTO;
import com.example.springlearnpr1.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class RegistrationValidator implements Validator {
    private final UserRepository userRepository;

    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDTO user = (UserRegistrationDTO) target;
        if(userRepository.existsByUsername(user.getUsername())){
            errors.rejectValue("username","","User already exists");
        }
        if(!user.getMatchingPassword().equals(user.getPassword())){
            errors.rejectValue("matchingPassword","","The passwords don't match");
        }
    }
}
