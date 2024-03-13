package n1b3lung0.apiGym.exercise_series.domain;

import n1b3lung0.apiGym.common.domain.criteria.Page;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseSeriesRepository {

    Page<ExerciseSeries> find(ExerciseSeriesSearchCriteria criteria);
    Optional<ExerciseSeries> findById(UUID id);
    ExerciseSeries save(ExerciseSeries exerciseSeries);
    void deleteById(UUID id);
}
