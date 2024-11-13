package com.alberto.workoutapp.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.alberto.workoutapp.entities.User;
import com.alberto.workoutapp.entities.Workout;

public class UserDTO {
    
    private Long id;
    private String name;
    private String email;
    private List<String> workouts = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        for (Workout workout : entity.getWorkouts()) {
            workouts.add(workout.getName());
        }
        for (GrantedAuthority role : entity.getAuthorities()) {
            roles.add(role.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getWorkouts() {
        return workouts;
    }

    public List<String> getRoles() {
        return roles;
    }


}
