package com.example.springlearnpr1.models;
import jakarta.persistence.*;
import lombok.*;
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

}
