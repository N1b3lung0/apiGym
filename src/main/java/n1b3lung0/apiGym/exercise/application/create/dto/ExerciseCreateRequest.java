package n1b3lung0.apiGym.exercise.application.create.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.domain.Exercise;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateRequest {

    @NotBlank
    @Size(max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    private String name;

    private String description;

    private String image;

    private String video;

    private String restTime;

    public Exercise toExercise(
            String name,
            String description,
            String image,
            String video,
            String restTime
    ) {
        return Exercise.create(
                name,
                description,
                image,
                video,
                restTime
        );
    }

    public static ExerciseCreateRequest fromExercise(Exercise exercise) {
        return new ExerciseCreateRequest(
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getRestTime()
        );
    }
}
