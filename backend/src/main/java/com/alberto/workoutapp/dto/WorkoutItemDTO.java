package com.alberto.workoutapp.dto;

import com.alberto.workoutapp.entities.WorkoutItem;

public class WorkoutItemDTO {

    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private Integer setNumber;
    private String reps;
    private Integer rest;
    private Integer weight;
    private String video;

    public WorkoutItemDTO(Long id, Long exerciseId, String exerciseName, Integer setNumber, String reps,
            Integer rest, Integer weight, String video) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.setNumber = setNumber;
        this.reps = reps;
        this.rest = rest;
        this.weight = weight;
        this.video = video;
    }

    public WorkoutItemDTO(WorkoutItem entity) {
        id = entity.getId();

        if (entity.getExercise() != null) {
            exerciseId = entity.getExercise().getId();
            exerciseName = entity.getExercise().getName();
            video = entity.getExercise().getVideo();
        }

        setNumber = entity.getSetNumber();
        reps = entity.getReps();
        rest = entity.getRest();
        weight = entity.getWeight();
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

    public String getReps() {
        return reps;
    }

    public Integer getRest() {
        return rest;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getVideo() {
        return video;
    }
}
