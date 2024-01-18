package n1b3lung0.apiGym.exercise.application.update;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.domain.ExerciseChange;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExerciseUpdater.class)
class ExerciseUpdaterTests extends BaseUnitTest {

    @Autowired
    private ExerciseUpdater updater;

    @MockBean
    private ExerciseFinder finder;

    @MockBean
    private ExerciseRepository repository;

    @Test
    void shouldUpdateAnExerciseByIdIfOnlyOneFieldHasBeenModified() {

        var exercise = ExerciseMother.random().description("Original description").build();

        when(finder.findById(String.valueOf(exercise.getId()))).thenReturn(exercise);

        var updatedExercise = exercise.withDescription("New description added");

        when(repository.save(updatedExercise)).thenReturn(updatedExercise);

        var expectedChanges = Collections.singletonList(ExerciseChange.create("description", updatedExercise.getDescription()));

        var request = new ExerciseUpdateRequest(
                String.valueOf(updatedExercise.getId()),
                null,
                updatedExercise.getDescription(),
                null,
                null,
                null
        );

        var actual = updater.updateFields(request);


    }

    @Test
    void shouldUpdateAnExerciseByIdWithSeveralFieldsModified() {

    }

    @Test
    void shouldNotUpdateIfSameValues() {

    }

    @Test
    void shouldOnlyUpdateChangedValues() {

    }
}
