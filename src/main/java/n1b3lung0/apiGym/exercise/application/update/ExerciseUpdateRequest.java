package n1b3lung0.apiGym.exercise.application.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Actualización de un ejercicio")
public class ExerciseUpdateRequest {

    @NotBlank
    @Schema(description = "Id del ejercicio")
    private String id;

    @NotBlank
    @Size(max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nuevo nombre del ejercicio")
    private String name;

    @Schema(description = "Nueva descripción del ejercio")
    private String  description;

    @Schema(description = "Nueva imagen del ejercicio")
    private String image;

    @Schema(description = "Nuevo video del ejercicio")
    private String video;

    @Schema(description = "Nuevo tiempo de descanso del ejercicio entre series")
    private String restTime;

    @Schema(description = "Intensidad con la que se ha hecho el ejercicio, de 1 a 10")
    @Min(value = 1) @Max(value = 10)
    private Integer intensity;
}
