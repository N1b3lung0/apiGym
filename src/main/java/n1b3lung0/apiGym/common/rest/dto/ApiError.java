package n1b3lung0.apiGym.common.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Schema(description = "Error de la API")
public class ApiError {

    @Schema(description = "Estado HTTP de la respuesta")
    private HttpStatus status;

    @Schema(description = "Mensaje de error")
    private String message;

    @Schema(description = "Lista detallada de errores")
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>();
    }
}
