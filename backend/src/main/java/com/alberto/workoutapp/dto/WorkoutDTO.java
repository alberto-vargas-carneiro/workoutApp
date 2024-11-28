package com.alberto.workoutapp.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.alberto.workoutapp.entities.Workout;
import com.alberto.workoutapp.entities.WorkoutItem;

import jakarta.validation.constraints.NotEmpty;

public class WorkoutDTO {
    
    private Long id;
    private String name;
    private Instant date;
    private UserMinDTO user;

    @NotEmpty(message = "Deve ter pelo menos um exercício")
    private List<WorkoutItemDTO> workoutItems = new ArrayList<>();

    public WorkoutDTO(Long id, String name, Instant date, UserMinDTO user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.user = user;
    }
    public WorkoutDTO(Workout entity) {
        id = entity.getId();
        name = entity.getName();
        date = entity.getDate();
        user = new UserMinDTO(entity.getUser());
        for (WorkoutItem workoutItem : entity.getWorkoutItem()) {
			WorkoutItemDTO workoutItemDTO = new WorkoutItemDTO(workoutItem);
			workoutItems.add(workoutItemDTO);
		};
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getDate() {
        return date;
    }
    public UserMinDTO getUser() {
        return user;
    }
    
    public List<WorkoutItemDTO> getWorkoutItems() {
        return workoutItems;
    }
}
