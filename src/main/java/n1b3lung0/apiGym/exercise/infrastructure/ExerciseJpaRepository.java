package n1b3lung0.apiGym.exercise.infrastructure;

import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseJpaRepository extends ExerciseRepository, JpaRepository<Exercise, UUID> {

    Optional<Exercise> findByIdAndDeletedFalse(UUID id);
}
