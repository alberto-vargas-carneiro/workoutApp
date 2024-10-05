package com.alberto.workoutapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.workoutapp.dto.ExerciseDTO;
import com.alberto.workoutapp.entities.Exercise;
import com.alberto.workoutapp.repositories.ExerciseRepository;
import com.alberto.workoutapp.services.exceptions.DatabaseException;
import com.alberto.workoutapp.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository repository;

    @Transactional(readOnly = true)
    public ExerciseDTO findById(Long id) {
        Exercise exercise = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ExerciseDTO(exercise);
    }

    @Transactional(readOnly = true)
    public Page<ExerciseDTO> findAll(Pageable pageable) {
        Page<Exercise> result = repository.findAll(pageable);
        return result.map(x -> new ExerciseDTO(x));
    }

    @Transactional
    public ExerciseDTO insert(ExerciseDTO dto) {
        Exercise entity = new Exercise();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ExerciseDTO(entity);
    }

    @Transactional
    public ExerciseDTO update(Long id, ExerciseDTO dto) {
        try {
            Exercise entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ExerciseDTO(entity);
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

    private void copyDtoToEntity(ExerciseDTO dto, Exercise entity) {
        entity.setName(dto.getName());
        entity.setVideo(dto.getVideo());
    }
}
