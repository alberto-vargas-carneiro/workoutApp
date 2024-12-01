package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.workoutapp.entities.WorkoutItem;

@Repository
public interface WorkoutItemRepository extends JpaRepository<WorkoutItem, Long> {
    
}
