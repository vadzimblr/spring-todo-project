package com.example.springlearnpr1.repositories;

import com.example.springlearnpr1.models.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User,Long> {
    List<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
