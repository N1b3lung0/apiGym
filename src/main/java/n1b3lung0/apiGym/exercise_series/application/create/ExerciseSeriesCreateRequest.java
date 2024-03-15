package n1b3lung0.apiGym.exercise_series.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.RestTime;
import n1b3lung0.apiGym.workout.domain.Workout;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data @NoArgsConstructor @AllArgsConstructor
@Schema(description = "Exercise series create request")
public class ExerciseSeriesCreateRequest {

    @Schema(description = "Workout which the exercise series belongs")
    private String workout_id;

    @Schema(description = "Exercise which the exercise series belongs")
    private String exercise_id;

    @Schema(description = "Exercise Series")
    private Set<String> seriesIds;

    @Schema(description = "Weight lifted in this exercise series")
    private Float weight;

    @Schema(description = "When the exercise series started")
    private ZonedDateTime startSeries;

    @Schema(description = "When the exercise series finished")
    private ZonedDateTime endSeries;

    @Schema(description = "Rest time between series")
    private RestTime restTime;

    public ExerciseSeries toExerciseSeries(
            Workout workout,
            Exercise exercise,
            Float weight,
            RestTime restTime
    ) {
        return ExerciseSeries.create(
                workout,
                exercise,
                weight,
                restTime
        );
    }

    public static ExerciseSeriesCreateRequest fromExerciseSeries(ExerciseSeries exerciseSeries) {
        return new ExerciseSeriesCreateRequest(
                String.valueOf(exerciseSeries.getWorkout().getId()),
                String.valueOf(exerciseSeries.getExercise().getId()),
                exerciseSeries.getSeries().stream()
                        .map(series -> series.getId().toString())
                        .collect(Collectors.toSet()),
                exerciseSeries.getWeight(),
                exerciseSeries.getStartSeries(),
                exerciseSeries.getEndSeries(),
                exerciseSeries.getRestTime()
        );
    }
}
