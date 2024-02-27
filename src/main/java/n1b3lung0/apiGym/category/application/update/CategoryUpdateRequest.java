package n1b3lung0.apiGym.category.application.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Actualización de una categoría")
public class CategoryUpdateRequest {

    @NotBlank
    @Schema(description = "Id de la categoría")
    private String id;

    @NotBlank
    @Size(min = 4, max = 255, message = ExceptionConstants.CATEGORY_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nuevo nombre de la categoría")
    private String name;
}
