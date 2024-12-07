package com.alberto.workoutapp.dto;

import jakarta.validation.constraints.Email;

public class UserNewDTO {
    
    private String name;
    @Email(message = "Email inválido")
    private String email;
    private String password;
    private String confirmPassword;

    public UserNewDTO(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
