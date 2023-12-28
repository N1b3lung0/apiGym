package n1b3lung0.apiGym.exercise.application.find.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise.domain.Exercise;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseResponse {

    private String id;
    private String name;
    private String description;
    private String image;
    private String video;
    private int restTime;

    public static ExerciseResponse fromExercise(Exercise exercise) {
        return exercise != null ? new ExerciseResponse(
                String.valueOf(exercise.getId()),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getRestTime()
        ) : new ExerciseResponse();
    }
}
