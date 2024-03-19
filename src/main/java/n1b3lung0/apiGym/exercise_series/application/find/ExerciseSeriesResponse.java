package n1b3lung0.apiGym.exercise_series.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise.application.find.ExerciseResponse;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.RestTime;
import n1b3lung0.apiGym.series.application.find.SeriesResponse;
import n1b3lung0.apiGym.workout.application.find.WorkoutResponse;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Exercise series response")
public class ExerciseSeriesResponse {

    @Schema(description = "Exercise series unique identifier")
    private String id;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Workout which this exercise series is linked")
    private WorkoutResponse workout;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Exercise which this exercise series is linked")
    private ExerciseResponse exercise;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Series linked to this Exercise series")
    private Set<SeriesResponse> series;

    @Schema(description = "Weight lifted in this exercise series")
    private Float weight;

    @Schema(description = "When the exercise series started")
    private ZonedDateTime startSeries;

    @Schema(description = "When the exercise series finished")
    private ZonedDateTime endSeries;

    @Schema(description = "Rest time between series")
    private RestTime restTime;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the exercise series was created")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who created the exercise series")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the exercise series was updated")
    private ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who updated the exercise series")
    private String updatedBy;

    public static ExerciseSeriesResponse fromExerciseSeries(ExerciseSeries exerciseSeries) {
        return exerciseSeries != null ? new ExerciseSeriesResponse(
                String.valueOf(exerciseSeries.getId()),
//                Optional.ofNullable(exerciseSeries.getWorkout()).map(workout -> String.valueOf(workout.getId())).orElse(StringUtils.EMPTY),
                exerciseSeries.getWorkout() != null
                        ? WorkoutResponse.fromWorkoutForExerciseSeries(exerciseSeries.getWorkout())
                        : null,
                exerciseSeries.getExercise() != null
                        ? ExerciseResponse.fromExercise(exerciseSeries.getExercise())
                        : null,
                exerciseSeries.getSeries() != null
                        ? exerciseSeries.getSeries().stream()
                        .map(SeriesResponse::fromSeries)
                        .collect(Collectors.toSet())
                        : null,
                exerciseSeries.getWeight(),
                exerciseSeries.getStartSeries(),
                exerciseSeries.getEndSeries(),
                exerciseSeries.getRestTime(),
                exerciseSeries.getAuditFields().getCreatedAt(),
                exerciseSeries.getAuditFields().getCreatedBy(),
                exerciseSeries.getAuditFields().getUpdatedAt(),
                exerciseSeries.getAuditFields().getUpdatedBy()
        ) : new ExerciseSeriesResponse();
    }
}
