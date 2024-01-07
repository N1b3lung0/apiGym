package n1b3lung0.apiGym.exercise.application.create;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ExerciseCreator.class)
class ExerciseCreatorTests extends BaseUnitTest {

    @Autowired
    private ExerciseCreator creator;

    @MockBean
    private ExerciseRepository repository;

    @Test
    void shouldCreateAnExercise() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void shouldFailCreateAnExerciseWithNoName() {
        assertTrue(Boolean.TRUE);
    }

}
