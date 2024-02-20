package n1b3lung0.apiGym.category.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Solicitud de creación de una categoría")
public class CategoryCreateRequest {

    @NotBlank
    @Size(min = 3, max = 255, message = ExceptionConstants.CATEGORY_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Nombre de la categoría")
    private String name;

    public Category toCategory(String name) {
        return Category.create(name);
    }

    public static CategoryCreateRequest fromCategory(Category category) {
        return new CategoryCreateRequest(category.getName());
    }
}
