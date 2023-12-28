package n1b3lung0.apiGym.exercise.integration;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseResponse;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.rest.ExerciseController;
import n1b3lung0.apiGym.mother.exercise.ExerciseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class ExerciseIntTests extends BaseIntegrationTest {

    @Autowired
    private ExerciseController controller;

    @SpyBean
    private ExerciseFinder finder;

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
}
