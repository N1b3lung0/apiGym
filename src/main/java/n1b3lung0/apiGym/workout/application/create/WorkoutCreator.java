package n1b3lung0.apiGym.workout.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesRepository;
import n1b3lung0.apiGym.exercise_series.domain.exception.ExerciseSeriesNotFound;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutCreator {

    private final WorkoutRepository repository;
    private final ExerciseSeriesRepository exerciseSeriesRepository;

    @Transactional
    public Workout create(WorkoutCreateRequest request) {
        Workout workout = repository.save(request.toWorkout(
                request.getStartWorkout(),
                request.getEndWorkout()
        ));
        Workout withExerciseSeries = (request.getExerciseSeriesIds() != null)
                ? addExerciseSeries(workout, request.getExerciseSeriesIds())
                : workout;
        Workout created = repository.save(withExerciseSeries);
        log.debug(String.format(LogConstants.WORKOUT_CREATED, created));
        return created;
    }

    private Workout addExerciseSeries(Workout workout, Set<String> exerciseSeriesIds) {
        Set<ExerciseSeries> exerciseSeries = exerciseSeriesIds.stream()
                .map(id -> exerciseSeriesRepository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new ExerciseSeriesNotFound(id)))
                .collect(Collectors.toSet());
        return workout.withExerciseSeries(exerciseSeries);
    }
}
