package com.alberto.workoutapp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alberto.workoutapp.dto.ExerciseDTO;
import com.alberto.workoutapp.services.ExerciseService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/exercises")
public class ExerciseController {
    
    @Autowired
    private ExerciseService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExerciseDTO> findById(@PathVariable Long id) {
        ExerciseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<ExerciseDTO>> findAll(Pageable pageable) {
        Page<ExerciseDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ExerciseDTO> insert(@RequestBody ExerciseDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExerciseDTO> update(@PathVariable Long id, @RequestBody ExerciseDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
