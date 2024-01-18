package n1b3lung0.apiGym.exercise.application.delete;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExerciseDeleter.class)
class ExerciseDeleterTests extends BaseUnitTest {

    @Autowired
    private ExerciseDeleter deleter;

    @MockBean
    private ExerciseFinder finder;

    @MockBean
    private ExerciseRepository repository;

    @Test
    void shouldMarkExerciseAsDeleted() {

        var exercise = ExerciseMother.random().build();
        var deleted = exercise.delete();

        when(finder.findById(String.valueOf(exercise.getId()))).thenReturn(exercise);
        when(repository.save(deleted)).thenReturn(deleted);

        deleter.delete(String.valueOf(exercise.getId()));
    }
}
