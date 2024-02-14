package n1b3lung0.apiGym.category.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.category.domain.Category;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Categoría/s que cumple/n con el criterio de búsqueda")
public class CategoryResponse {

    @Schema(description = "Identificador único de la categoría")
    private String id;

    @Schema(description = "Nombre de la categoría")
    private String name;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de creación de la categoría")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién creó la categoría")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de actualización de la categoría")
    private ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién actualizó la categoría")
    private String updatedBy;

    public static CategoryResponse fromCategory(Category category) {
        return category != null ? new CategoryResponse(
                String.valueOf(category.getId()),
                category.getName(),
                category.getAuditFields().getCreatedAt(),
                category.getAuditFields().getCreatedBy(),
                category.getAuditFields().getUpdatedAt(),
                category.getAuditFields().getUpdatedBy()
        ) : new CategoryResponse();
    }
}
