package n1b3lung0.apiGym.exercise.domain;

import n1b3lung0.apiGym.common.domain.criteria.Page;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository {
    Page<Exercise> find(ExerciseSearchCriteria criteria);
    Optional<Exercise> findByIdAndDeletedFalse(UUID id);
    Optional<Exercise> findByName(String name);
    Exercise save(Exercise exercise);
}
