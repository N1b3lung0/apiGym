package n1b3lung0.apiGym.exercise.application.create.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Solicitud de creación de un ejercicio")
public class ExerciseCreateRequest {

    @NotBlank
    @Size(max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nombre del ejercicio")
    private String name;

    @Schema(description = "Descripción del ejercicio")
    private String description;

    @Schema(description = "Imagen del ejercicio")
    private String image;

    @Schema(description = "Video del ejercicio")
    private String video;

    @Schema(description = "Tiempo de descanso del ejercicio entre series")
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
