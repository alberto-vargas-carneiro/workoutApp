package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.workoutapp.entities.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
}
