package n1b3lung0.apiGym.exercise.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise.domain.Exercise;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Ejercicio/s que cumple/n el criterio de búsqueda")
public class ExerciseResponse {

    @Schema(description = "Identificador único del ejercicio")
    private String id;

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

    @Schema(description = "Intensidad con la que se ha hecho el ejercicio, de 1 a 10")
    private Integer intensity;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de creación del ejercicio")
    private LocalDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién creó el ejercicio")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de actualización del ejercicio")
    private LocalDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién actualizó el ejercicio")
    private String updatedBy;

    public static ExerciseResponse fromExercise(Exercise exercise) {
        return exercise != null ? new ExerciseResponse(
                String.valueOf(exercise.getId()),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getRestTime(),
                exercise.getIntensity(),
                exercise.getAuditFields().getCreatedAt(),
                exercise.getAuditFields().getCreatedBy(),
                exercise.getAuditFields().getUpdatedAt(),
                exercise.getAuditFields().getUpdatedBy()
        ) : new ExerciseResponse();
    }
}
