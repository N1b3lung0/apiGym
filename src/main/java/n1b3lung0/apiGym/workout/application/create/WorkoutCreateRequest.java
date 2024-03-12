package n1b3lung0.apiGym.workout.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.workout.domain.Workout;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Workout create request")
public final class WorkoutCreateRequest {

    @Schema(description = "List of exercises with their series")
    private Set<String> exerciseSeriesIds;

    @Schema(description = "Workout Start Time")
    private ZonedDateTime startWorkout;

    @Schema(description = "Workout End Time")
    private ZonedDateTime endWorkout;

    public Workout toWorkout(
            ZonedDateTime startWorkout,
            ZonedDateTime endWorkout
    ) {
        return Workout.create(
                startWorkout,
                endWorkout
        );
    }

    public static WorkoutCreateRequest fromWorkout(Workout workout) {
        return new WorkoutCreateRequest(
                workout.getExerciseSeries().stream()
                        .map(exerciseSeries -> exerciseSeries.getId().toString())
                        .collect(Collectors.toSet()),
                workout.getStartWorkout(),
                workout.getEndWorkout()
        );
    }
}
