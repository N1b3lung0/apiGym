package n1b3lung0.apiGym.workout.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.workout.domain.Workout;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Workout response")
public class WorkoutResponse {

    @Schema(description = "Workout unique identifier")
    private String id;

    @Schema(description = "List of exercises with their series")
    private Set<ExerciseSeries> exerciseSeries;

    @Schema(description = "When the workout started")
    private ZonedDateTime startWorkout;

    @Schema(description = "When the workout finished")
    private ZonedDateTime endWorkout;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the workout was created")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who created the workout")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the workout was updated")
    private ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who updated the workout")
    private String updatedBy;

    public static WorkoutResponse fromWorkout(Workout workout) {
        return workout != null ? new WorkoutResponse(
                String.valueOf(workout.getId()),
                workout.getExerciseSeries(),
                workout.getStartWorkout(),
                workout.getEndWorkout(),
                workout.getAuditFields().getCreatedAt(),
                workout.getAuditFields().getCreatedBy(),
                workout.getAuditFields().getUpdatedAt(),
                workout.getAuditFields().getUpdatedBy()
        ) : new WorkoutResponse();
    }
}
