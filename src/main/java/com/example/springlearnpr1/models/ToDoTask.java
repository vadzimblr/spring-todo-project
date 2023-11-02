package com.example.springlearnpr1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "tasks")
@Data
public class ToDoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Task name could not be empty!")
    @Size(min = 2, max = 96, message = "Username should be between 2 and 96 characters")
    private String name;
    @Column(name = "iscompleted")
    private Boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
    @Override
    public String toString() {
        return "ToDoTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
