package n1b3lung0.apiGym.exercise.integration;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreator;
import n1b3lung0.apiGym.exercise.application.create.dto.ExerciseCreateRequest;
import n1b3lung0.apiGym.exercise.application.delete.ExerciseDeleter;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.find.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdater;
import n1b3lung0.apiGym.exercise.application.update.dto.ExerciseUpdateRequest;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.rest.ExerciseController;
import n1b3lung0.apiGym.mother.exercise.ExerciseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExerciseIntTests extends BaseIntegrationTest {

    @Autowired
    private ExerciseController controller;

    @SpyBean
    private ExerciseFinder finder;

    @SpyBean
    private ExerciseCreator creator;

    @SpyBean
    private ExerciseUpdater updater;

    @SpyBean
    private ExerciseDeleter deleter;

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

    @Test
    void shouldCreateAValidExercise() {
        var request = ExerciseCreateRequest.fromExercise(exercise);
        var expected = creator.create(request);
        var response = controller.create(request);
        var responseId = Objects.requireNonNull(response.getHeaders().getLocation())
                .getPath().replace("/api/exercises/", "");
        var location = UriComponentsBuilder.fromPath("/api/exercises/{id}")
                .buildAndExpand(UUIDUtils.fromString(responseId)).toUri();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        assertTrue(StringUtils.isNotBlank(responseId));
        assertEquals(location, response.getHeaders().getLocation());
        assertNotNull(em.find(Exercise.class, expected.getId()));
    }

    @Test
    void shouldUpdateAnExercise() {
        var updatedExercise = exercise.withDescription("Description updated");
        var request = new ExerciseUpdateRequest(
                String.valueOf(updatedExercise.getId()),
                null,
                updatedExercise.getDescription(),
                null,
                null,
                null
        );
        var expected = ExerciseResponse.fromExercise(updatedExercise);
        var response = controller.update(request);

        verify(updater).updateFields(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        var actual = em.find(Exercise.class, updatedExercise.getId());
        assertEquals(updatedExercise, actual);
    }

    @Test
    void shouldDeleteAnExercise() {
        var id = String.valueOf(exercise.getId());
        var response = controller.delete(id);

        verify(deleter).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        var actual = em.find(Exercise.class, exercise.getId());
        assertTrue(actual.isDeleted());
    }
}
