package n1b3lung0.apiGym.exercise.infrastructure;

import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.ExerciseSearchCriteria;

import java.util.Optional;
import java.util.UUID;

public class ExerciseRepositoryMock implements ExerciseRepository {

    @Override
    public Page<Exercise> find(ExerciseSearchCriteria criteria) {
        return null;
    }

    @Override
    public Optional<Exercise> findByIdAndDeletedFalse(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Exercise> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Exercise save(Exercise exercise) {
        return new Exercise();
    }
}
