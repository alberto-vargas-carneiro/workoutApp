package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.workoutapp.entities.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
