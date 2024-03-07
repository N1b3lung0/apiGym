package n1b3lung0.apiGym.workout.infrastructure;

import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WorkoutJpaRepository extends WorkoutRepository, JpaRepository<Workout, UUID>, JpaSpecificationExecutor<Workout> {
}
