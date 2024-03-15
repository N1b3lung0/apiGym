package n1b3lung0.apiGym.exercise_series.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesRepository;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.series.domain.SeriesRepository;
import n1b3lung0.apiGym.series.domain.exception.SeriesNotFound;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import n1b3lung0.apiGym.workout.domain.exception.WorkoutNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseSeriesCreator {

    private final ExerciseSeriesRepository repository;
    private final SeriesRepository seriesRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public ExerciseSeries create(ExerciseSeriesCreateRequest request) {
        ExerciseSeries exerciseSeries = repository.save(request.toExerciseSeries(
                addWorkout(request.getWorkout_id()),
                addExercise(request.getExercise_id()),
                request.getWeight(),
                request.getRestTime()
                )
        );
        ExerciseSeries withSeries = (request.getSeriesIds() != null)
                ? addSeries(exerciseSeries, request.getSeriesIds())
                : exerciseSeries;
        ExerciseSeries created = repository.save(withSeries);
        log.debug(LogConstants.EXERCISE_SERIES_CREATED, created);
        return created;
    }

    private ExerciseSeries addSeries(ExerciseSeries exerciseSeries, Set<String> seriesIds) {
        Set<Series> series = seriesIds.stream()
                .map(id -> seriesRepository.findById(UUIDUtils.fromString(id))
                        .orElseThrow(() -> new SeriesNotFound(id)))
                .collect(Collectors.toSet());
        return exerciseSeries.withSeries(series);
    }

    private Workout addWorkout(String workout_id) {
        return workoutRepository.findById(UUIDUtils.fromString(workout_id))
                .orElseThrow(() -> new WorkoutNotFound(workout_id));
    }

    private Exercise addExercise(String exercise_id) {
        return exerciseRepository.findByIdAndActiveTrue(UUIDUtils.fromString(exercise_id))
                .orElseThrow(() -> new SeriesNotFound(exercise_id));
    }
}
