package n1b3lung0.apiGym.exercise_series.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.RestTime;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.workout.domain.Workout;

import java.time.ZonedDateTime;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Exercise series response")
public class ExerciseSeriesResponse {

    @Schema(description = "Exercise series unique identifier")
    private String id;

    @Schema(description = "Workout which this exercise series is linked")
    private Workout workout;

    @Schema(description = "Exercise which this exercise series is linked")
    private Exercise exercise;

    @Schema(description = "Series linked to this Exercise series")
    private Set<Series> series;

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
                exerciseSeries.getWorkout(),
                exerciseSeries.getExercise(),
                exerciseSeries.getSeries(),
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
