package n1b3lung0.apiGym.exercise.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.category.application.find.CategoryResponse;
import n1b3lung0.apiGym.exercise.domain.Exercise;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Schema(description = "Intensidad con la que se ha hecho el ejercicio, de 1 a 10")
    private Integer intensity;

    @Schema(description = "Categorías a las que pertenece el ejercicio")
    private Set<CategoryResponse> categories;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de creación del ejercicio")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién creó el ejercicio")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de actualización del ejercicio")
    private ZonedDateTime updatedAt;

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
                exercise.getIntensity(),
                exercise.getCategories().stream().map(CategoryResponse::fromCategory).collect(Collectors.toSet()),
                exercise.getAuditFields().getCreatedAt(),
                exercise.getAuditFields().getCreatedBy(),
                exercise.getAuditFields().getUpdatedAt(),
                exercise.getAuditFields().getUpdatedBy()
        ) : new ExerciseResponse();
    }
}
