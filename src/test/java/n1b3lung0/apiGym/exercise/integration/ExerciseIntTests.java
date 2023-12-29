package n1b3lung0.apiGym.exercise.integration;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.find.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.rest.ExerciseController;
import n1b3lung0.apiGym.mother.exercise.ExerciseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExerciseIntTests extends BaseIntegrationTest {

    @Autowired
    private ExerciseController controller;

    @SpyBean
    private ExerciseFinder finder;

    @SpyBean
    private ExerciseRepository repository;

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        exercise = ExerciseMother.random().id(null).build();
        em.persist(exercise);
    }

    @Test
    void shouldFindAnExerciseById() {
        var id = String.valueOf(exercise.getId());
        var expected = ExerciseResponse.fromExercise(exercise);
        var response = controller.findById(id);

        verify(finder).findById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    void shouldFailFindAnExerciseByIdIfNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> finder.findById(null));

        verify(repository, never()).findByIdAndDeletedFalse(UUID.randomUUID());
        assertEquals(ExceptionConstants.ID_REQUIRED, e.getMessage());
    }

    @Test
    void shouldFailFindAnExerciseByIdIfNotFound() {
        var id = UUID.randomUUID();
        when(repository.findByIdAndDeletedFalse(id)).thenReturn(Optional.empty());

        var e = assertThrows(ExerciseNotFound.class, () -> finder.findById(id.toString()));
        assertEquals(String.format(ExceptionConstants.EXERCISE_NOT_FOUND, id), e.getMessage());
    }
}
