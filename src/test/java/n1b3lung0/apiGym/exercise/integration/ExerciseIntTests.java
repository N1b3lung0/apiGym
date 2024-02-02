package n1b3lung0.apiGym.exercise.integration;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreateRequest;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreator;
import n1b3lung0.apiGym.exercise.application.delete.ExerciseDeleter;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdateRequest;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdater;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import n1b3lung0.apiGym.exercise.rest.ExerciseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
    private void setUp() {
        exercise = ExerciseMother.random().id(null).build();
        em.persist(exercise);
    }

    @Test
    void shouldFindAnExerciseById() {

        var id = String.valueOf(exercise.getId());
        var expected = ExerciseResponse.fromExercise(exercise);

        var actual = controller.findById(id);

        verify(finder).findById(id);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    void shouldFailFindAnExerciseByIdIfNull() {

        var expected = ExceptionConstants.ID_REQUIRED;

        var actual = assertThrows(IllegalArgumentException.class, () -> finder.findById(null));

        verify(repository, never()).findByIdAndDeletedFalse(any());
        assertEquals(expected, actual.getMessage());
    }

    @Test
    void shouldFailFindAnExerciseByIdIfNotFound() {

        var id = UUID.randomUUID();
        var expected = String.format(ExceptionConstants.EXERCISE_NOT_FOUND, id);
        when(repository.findByIdAndDeletedFalse(id)).thenReturn(Optional.empty());

        var actual = assertThrows(ExerciseNotFound.class, () -> finder.findById(String.valueOf(id)));
        assertEquals(expected, actual.getMessage());
    }

    @Test
    void shouldCreateAValidExercise() {

        var expected = ExerciseMother.random().id(null).build();
        var request = ExerciseCreateRequest.fromExercise(expected);

        var actual = controller.create(request);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertNull(actual.getBody());
        var actualLocation = actual.getHeaders().getLocation();
        var actualId = Objects.requireNonNull(actualLocation)
                .getPath().replace("/api/exercises/", "");
        assertTrue(StringUtils.isNotBlank(actualId));
        var expectedLocation = UriComponentsBuilder.fromPath("/api/exercises/{id}")
                .buildAndExpand(UUIDUtils.fromString(actualId)).toUri();
        assertEquals(expectedLocation, actualLocation);
        var actualUUID = UUID.fromString(actualId);
        var actualExercise = em.find(Exercise.class, actualUUID);
        assertEquals(expected.withId(actualUUID), actualExercise);
    }

    @Test
    void shouldFailIfNameAlreadyExists() {

        var newExercise = ExerciseMother.random().id(null).name(exercise.getName()).build();
        var request = ExerciseCreateRequest.fromExercise(newExercise);
        var expected = String.format(ExceptionConstants.EXERCISE_REPEATED, newExercise.getName());

        var actual = assertThrows(RuntimeException.class, () -> controller.create(request));

        assertEquals(expected, actual.getMessage());
        var actualExercises = em.createQuery(String.format("select e from Exercise e where name = '%s'", newExercise.getName())).getResultList();
        assertEquals(1, actualExercises.size());
        assertEquals(exercise, actualExercises.get(0));
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
                null,
                null
        );
        var expected = ExerciseResponse.fromExercise(updatedExercise);
        var response = controller.update(request);

        verify(updater).updateFields(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        var actual = em.find(Exercise.class, exercise.getId());
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
