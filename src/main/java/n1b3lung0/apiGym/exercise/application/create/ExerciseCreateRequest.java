package n1b3lung0.apiGym.exercise.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.RestTime;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Solicitud de creación de un ejercicio")
public class ExerciseCreateRequest {

    @NotBlank
    @Size(min = 4, max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nombre del ejercicio")
    private String name;

    @Schema(description = "Descripción del ejercicio")
    private String description;

    @Schema(description = "Imagen del ejercicio")
    private String image;

    @Schema(description = "Video del ejercicio")
    private String video;

    @Schema(description = "Tiempo de descanso del ejercicio entre series")
    private RestTime restTime;

    @Schema(description = "Intensidad con la que se ha hecho el ejercicio, de 1 a 10")
    @Min(value = 1, message = ExceptionConstants.EXERCISE_INTENSITY_MIN)
    @Max(value = 10, message = ExceptionConstants.EXERCISE_INTENSITY_MAX)
    private Integer intensity;

    @Schema(description = "Categorías a las que pertenece el ejercicio")
    private Set<String> categoryIds;

    public Exercise toExercise(
            String name,
            String description,
            String image,
            String video,
            RestTime restTime,
            Integer intensity
    ) {
        return Exercise.create(
                name,
                description,
                image,
                video,
                restTime,
                intensity
        );
    }

    public static ExerciseCreateRequest fromExercise(Exercise exercise) {
        return new ExerciseCreateRequest(
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getRestTime(),
                exercise.getIntensity(),
                exercise.getCategories().stream()
                        .map(category -> category.getId().toString())
                        .collect(Collectors.toSet())
        );
    }
}
