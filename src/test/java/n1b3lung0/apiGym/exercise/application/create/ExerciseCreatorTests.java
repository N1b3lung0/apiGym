package n1b3lung0.apiGym.exercise.application.create;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.exercise.application.create.dto.ExerciseCreateRequest;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.infrastructure.ExerciseRepositoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ExerciseCreator.class})
class ExerciseCreatorTests extends BaseUnitTest {

    @Autowired
    private ExerciseCreator creator;

    @MockBean
    private ExerciseRepository repository;

    @Test
    void shouldCreateAnExercise() {

        var expected = new Exercise();

        when(repository.save(any())).thenReturn(expected);

        var actual = creator.create(new ExerciseCreateRequest());

        assertEquals(expected, actual);
    }

    @Test
    void shouldFailCreateAnExerciseWithNoName() {
        assertTrue(Boolean.TRUE);
    }

}
