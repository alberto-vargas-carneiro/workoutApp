package com.alberto.workoutapp.dto;

import com.alberto.workoutapp.entities.WorkoutItem;

public class WorkoutItemDTO {

    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private Integer setNumber;
    private Integer reps;
    private Integer rest;
    private String video;


    public WorkoutItemDTO(Long id, Long exerciseId, String exerciseName, Integer setNumber, Integer reps,
            Integer rest, String video) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.setNumber = setNumber;
        this.reps = reps;
        this.rest = rest;
        this.video = video;
    }

    public WorkoutItemDTO(WorkoutItem entity) {
        id = entity.getId();
        exerciseId = entity.getExercise().getId();
        exerciseName = entity.getExercise().getName();
        setNumber = entity.getSetNumber();
        reps = entity.getReps();
        rest = entity.getRest();
        video = entity.getExercise().getVideo();
    }

    public Long getId() {
        return id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public Integer getReps() {
        return reps;
    }

    public Integer getRest() {
        return rest;
    }

    public String getVideo() {
        return video;
    }
}
