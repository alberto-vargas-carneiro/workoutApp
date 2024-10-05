package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alberto.workoutapp.entities.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
}
