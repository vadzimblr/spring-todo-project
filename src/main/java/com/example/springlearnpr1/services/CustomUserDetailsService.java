package com.example.springlearnpr1.services;

import com.example.springlearnpr1.models.User;
import com.example.springlearnpr1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.existsByUsername(username) == true){
            User user = userRepository.findByUsername(username).get(0);

            return null;
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }


    }
}
