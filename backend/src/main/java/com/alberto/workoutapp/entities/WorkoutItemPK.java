package com.alberto.workoutapp.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class WorkoutItemPK {
    
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
    
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public WorkoutItemPK() {
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workout == null) ? 0 : workout.hashCode());
        result = prime * result + ((exercise == null) ? 0 : exercise.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WorkoutItemPK other = (WorkoutItemPK) obj;
        if (workout == null) {
            if (other.workout != null)
                return false;
        } else if (!workout.equals(other.workout))
            return false;
        if (exercise == null) {
            if (other.exercise != null)
                return false;
        } else if (!exercise.equals(other.exercise))
            return false;
        return true;
    }
}
