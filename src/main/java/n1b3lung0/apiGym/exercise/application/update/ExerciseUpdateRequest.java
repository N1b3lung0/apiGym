package n1b3lung0.apiGym.exercise.application.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.domain.Image;
import n1b3lung0.apiGym.exercise.domain.Video;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Actualización de un ejercicio")
public class ExerciseUpdateRequest {

    @NotBlank
    @Schema(description = "Id del ejercicio")
    private String id;

    @NotBlank
    @Size(min = 4, max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nuevo nombre del ejercicio")
    private String name;

    @Schema(description = "Nueva descripción del ejercio")
    private String  description;

    @Schema(description = "Nueva imagen del ejercicio")
    private Image image;

    @Schema(description = "Nuevo video del ejercicio")
    private Video video;

    @Schema(description = "Intensidad con la que se ha hecho el ejercicio, de 1 a 10")
    @Min(value = 1, message = ExceptionConstants.EXERCISE_INTENSITY_MIN)
    @Max(value = 10, message = ExceptionConstants.EXERCISE_INTENSITY_MAX)
    private Integer intensity;

    @Schema(description = "Categorías a las que pertenece el ejercicio")
    private Set<String> categoryIds;
}
