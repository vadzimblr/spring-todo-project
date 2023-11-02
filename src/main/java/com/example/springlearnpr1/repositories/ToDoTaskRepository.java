package com.example.springlearnpr1.repositories;

import com.example.springlearnpr1.models.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoTaskRepository extends JpaRepository<ToDoTask,Long> {
    List<ToDoTask> findByUserId(Long userId);
}
