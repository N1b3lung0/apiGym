package n1b3lung0.apiGym.exercise.application.find;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExerciseFinder.class)
class ExerciseFinderTests extends BaseUnitTest {

    @Autowired
    private ExerciseFinder finder;

    @MockBean
    private ExerciseRepository repository;

    @Test
    void shouldFindAnExerciseByIdIfNotDeleted() {

        var expected = ExerciseMother.random().build();

        when(repository.findByIdAndDeletedFalse(expected.getId())).thenReturn(Optional.of(expected));

        var actual = finder.findById(String.valueOf(expected.getId()));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFailIfIdNotFound() {

        var id = UUID.randomUUID();

        when(repository.findByIdAndDeletedFalse(id)).thenReturn(Optional.empty());

        var actual = assertThrows(ExerciseNotFound.class, () -> finder.findById(String.valueOf(id)));

        assertEquals(String.format(ExceptionConstants.EXERCISE_NOT_FOUND, id), actual.getMessage());
    }

    @Test
    void shouldFailIfExerciseIsDeleted() {

        var exercise = ExerciseMother.random().deleted(Boolean.TRUE).build();

        when(repository.findByIdAndDeletedFalse(exercise.getId())).thenReturn(Optional.empty());

        var actual = assertThrows(ExerciseNotFound.class, () -> finder.findById(String.valueOf(exercise.getId())));

        assertEquals(String.format(ExceptionConstants.EXERCISE_NOT_FOUND, exercise.getId()), actual.getMessage());
    }
}
