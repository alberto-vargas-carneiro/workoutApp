package com.alberto.workoutapp.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_workout_item")
public class WorkoutItem {
    
    @EmbeddedId
    private WorkoutItemPK id = new WorkoutItemPK();
    private Integer sets;
    private Integer reps;
    private Integer rest;

    public WorkoutItem() {
    }

    public WorkoutItem(Workout workout, Exercise exercise, Integer sets, Integer reps, Integer rest) {
        id.setWorkout(workout);
        id.setExercise(exercise);
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }

    public Workout getWorkout() {
        return id.getWorkout();
    }

    public void setWorkout(Workout workout) {
        id.setWorkout(workout);
    }
    public Exercise getExercise() {
        return id.getExercise();
    }

    public void setExercise(Exercise exercise) {
        id.setExercise(exercise);
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        WorkoutItem other = (WorkoutItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
