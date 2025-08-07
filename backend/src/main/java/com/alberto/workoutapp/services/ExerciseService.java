package com.alberto.workoutapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.workoutapp.dto.ExerciseDTO;
import com.alberto.workoutapp.entities.Exercise;
import com.alberto.workoutapp.repositories.ExerciseRepository;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository repository;

    @Transactional(readOnly = true)
    public Page<ExerciseDTO> findAll(Pageable pageable) {
        Page<Exercise> result = repository.findAll(pageable);
        return result.map(x -> new ExerciseDTO(x));
    }
}
