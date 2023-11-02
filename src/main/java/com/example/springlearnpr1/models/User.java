package com.example.springlearnpr1.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The username field should not be empty")
    @Size(min= 2, max=48,message = "Username should be between 2 and 48 characters")
    private String username;
    @NotBlank(message = "The password field should not be empty")
    @Size(min = 6,message = "Password should be greater than 6 characters")
    private String password;
    private Boolean enabled = true;
    @NotBlank(message = "The email field should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ToDoTask> tasks = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
    @Override
    public String toString() {
        Hibernate.initialize(tasks);
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", tasks=" + tasks.toString() +
                '}';
    }

}
