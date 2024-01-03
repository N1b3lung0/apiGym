package n1b3lung0.apiGym.exercise.domain;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository {

    Optional<Exercise> findByIdAndDeletedFalse(UUID id);

    Optional<Exercise> findByName(String name);

    Exercise save(Exercise exercise);
}
