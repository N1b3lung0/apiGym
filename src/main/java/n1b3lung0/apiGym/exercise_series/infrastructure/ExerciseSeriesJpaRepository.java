package n1b3lung0.apiGym.exercise_series.infrastructure;

import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExerciseSeriesJpaRepository extends ExerciseSeriesRepository, JpaRepository<ExerciseSeries, UUID>, JpaSpecificationExecutor<ExerciseSeries> {

}
