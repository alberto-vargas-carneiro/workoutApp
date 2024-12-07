package com.alberto.workoutapp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alberto.workoutapp.dto.UserDTO;
import com.alberto.workoutapp.dto.UserMinDTO;
import com.alberto.workoutapp.dto.UserNewDTO;
import com.alberto.workoutapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserMinDTO> insert(@Valid @RequestBody UserNewDTO userNewDto) {
        UserMinDTO userMinDto = service.insert(userNewDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userMinDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userMinDto);
    }
}
