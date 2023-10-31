package com.example.springlearnpr1.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegistrationDTO extends User{
    @NotBlank(message = "The password field should not be empty")
    private String matchingPassword;
}
