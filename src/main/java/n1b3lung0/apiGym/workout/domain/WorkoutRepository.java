package n1b3lung0.apiGym.workout.domain;

import n1b3lung0.apiGym.common.domain.criteria.Page;

import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepository {
    Page<Workout> find(WorkoutSearchCriteria criteria);
    Optional<Workout> findById(UUID id);
    Workout save(Workout workout);
    void deleteById(UUID id);
}
