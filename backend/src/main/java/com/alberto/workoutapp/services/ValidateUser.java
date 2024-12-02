package com.alberto.workoutapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.workoutapp.entities.User;
import com.alberto.workoutapp.services.exceptions.ForbiddenException;

@Service
public class ValidateUser {
    
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    }
}
