package com.example.springlearnpr1.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


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
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
    @NotBlank(message = "The email field should not be empty")
    @Email(message = "Email should be valid")
    private String email;

}
