package com.example.springlearnpr1.models;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
@Data
//@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private final Long id;
    private String username;
    private String password;
    private Boolean enabled;
}
