package com.alberto.workoutapp.dto;

import com.alberto.workoutapp.entities.Exercise;

public class ExerciseDTO {
    
    private Long id;
    private String name;
    private String video;

    public ExerciseDTO(Long id, String name, String video) {
        this.id = id;
        this.name = name;
        this.video = video;
    }
    public ExerciseDTO(Exercise entity) {
        id = entity.getId();
        name = entity.getName();
        video = entity.getVideo();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVideo() {
        return video;
    }
}
