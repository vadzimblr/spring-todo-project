package com.example.springlearnpr1.repositories;

import com.example.springlearnpr1.models.ToDoTask;
import com.example.springlearnpr1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoTaskRepository extends JpaRepository<ToDoTask,Long> {
    List<ToDoTask> findByUserId(Long userId);
    Optional<ToDoTask> findByIdAndUser(Long id, User user);
}
