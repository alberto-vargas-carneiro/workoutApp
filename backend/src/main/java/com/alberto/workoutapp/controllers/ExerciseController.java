package com.alberto.workoutapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.workoutapp.dto.ExerciseDTO;
import com.alberto.workoutapp.services.ExerciseService;

@RestController
@RequestMapping(value = "/exercises")
public class ExerciseController {
    
    @Autowired
    private ExerciseService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<ExerciseDTO>> findAll(Pageable pageable) {
        Page<ExerciseDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
