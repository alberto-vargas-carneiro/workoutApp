package com.alberto.workoutapp.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.workoutapp.dto.WorkoutDTO;
import com.alberto.workoutapp.dto.WorkoutItemDTO;
import com.alberto.workoutapp.entities.Exercise;
import com.alberto.workoutapp.entities.User;
import com.alberto.workoutapp.entities.Workout;
import com.alberto.workoutapp.entities.WorkoutItem;
import com.alberto.workoutapp.repositories.ExerciseRepository;
import com.alberto.workoutapp.repositories.WorkoutItemRepository;
import com.alberto.workoutapp.repositories.WorkoutRepository;
import com.alberto.workoutapp.services.exceptions.DatabaseException;
import com.alberto.workoutapp.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutService {
    
    @Autowired
    private WorkoutRepository repository;

    @Autowired
    private WorkoutItemRepository workoutItemRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateUser validateUser;

    @Transactional(readOnly = true)
    public WorkoutDTO findById(Long id) {
        Workout workout = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        validateUser.validateSelfOrAdmin(workout.getUser().getId());
        return new WorkoutDTO(workout);
    }

    @Transactional
    public WorkoutDTO insert(WorkoutDTO dto) {
        Workout entity = new Workout();
        
        copyDtoToEntity(dto, entity);

        entity.setDate(Instant.now());

        User user = userService.authenticated();
    	entity.setUser(user);

        for (WorkoutItemDTO workoutItemDto : dto.getWorkoutItems()) {
            Exercise exercise = exerciseRepository.getReferenceById(workoutItemDto.getExerciseId());
            WorkoutItem workoutItem = new WorkoutItem(workoutItemDto.getId(), entity, exercise, workoutItemDto.getSetNumber(), workoutItemDto.getReps(), workoutItemDto.getRest());
            entity.getWorkoutItem().add(workoutItem);
        }
        repository.save(entity);
        workoutItemRepository.saveAll(entity.getWorkoutItem());

        return new WorkoutDTO(entity);
    }

    @Transactional
    public WorkoutDTO update(Long id, WorkoutDTO dto) {
        try {
            Workout entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new WorkoutDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    private void copyDtoToEntity(WorkoutDTO dto, Workout entity) {
        entity.setName(dto.getName());
        // entity.setDate(dto.getDate());
        // entity.setUser(dto.getUser());
    }
}
