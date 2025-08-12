package com.alberto.workoutapp.services;

import static org.mockito.ArgumentMatchers.any;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alberto.workoutapp.dto.WorkoutDTO;
import com.alberto.workoutapp.entities.User;
import com.alberto.workoutapp.entities.Workout;
import com.alberto.workoutapp.repositories.ExerciseRepository;
import com.alberto.workoutapp.repositories.WorkoutItemRepository;
import com.alberto.workoutapp.repositories.WorkoutRepository;
import com.alberto.workoutapp.services.exceptions.ForbiddenException;
import com.alberto.workoutapp.services.exceptions.ResourceNotFoundException;
import com.alberto.workoutapp.tests.UserFactory;

@ExtendWith(SpringExtension.class)
public class WorkoutServiceTests {

    @InjectMocks
    private WorkoutService workoutService;

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private WorkoutItemRepository workoutItemRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private UserService userService;

    @Mock
    private ValidateUser validateUser;

    private Workout workout;
    private WorkoutDTO workoutDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        user = UserFactory.createClientUser(1L, "email@email.com");
        workout = new Workout(1L, "Morning Workout", Instant.ofEpochSecond(1633036800), user);

        workoutDTO = new WorkoutDTO(workout);

        Mockito.when(workoutRepository.findById(1L)).thenReturn(Optional.of(workout));
    }

    @Test
    public void findById_ShouldReturnWorkoutDTO_WhenWorkoutExistsAndClientLogged() {

        Mockito.doNothing().when(validateUser).validateSelfOrAdmin(any());

        WorkoutDTO result = workoutService.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(workout.getId(), result.getId());
        Assertions.assertEquals(workout.getName(), result.getName());
        Assertions.assertEquals(workout.getDate(), result.getDate());
        Assertions.assertEquals(workout.getUser().getId(), result.getUser().getId());
    }

    @Test
    public void findById_ShouldThrowForbiddenException_WhenIdExistsAndOtherClientLogged() {

        Mockito.doThrow(ForbiddenException.class).when(validateUser).validateSelfOrAdmin(any());

        Assertions.assertThrows(ForbiddenException.class, () -> {
            @SuppressWarnings("unused")
            WorkoutDTO result = workoutService.findById(1L);
        });
    }

    @Test
    public void findById_ShouldThrowResourceNotFoundException_WhenIdDoesNotExist() {

        Mockito.doNothing().when(validateUser).validateSelfOrAdmin(any());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            @SuppressWarnings("unused")
            WorkoutDTO result = workoutService.findById(2L);
        });
    }

    @Test
    public void insert_ShouldInsertWorkout_WhenValidData() {

        Mockito.when(userService.authenticated()).thenReturn(user);

        WorkoutDTO result = workoutService.insert(workoutDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(workoutDTO.getName(), result.getName());
        Assertions.assertEquals(user.getId(), result.getUser().getId());
    }
}
