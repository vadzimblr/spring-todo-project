package com.example.springlearnpr1.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority_name")
    private String authorityName;
}
