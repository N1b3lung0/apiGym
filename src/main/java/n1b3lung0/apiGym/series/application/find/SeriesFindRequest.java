package n1b3lung0.apiGym.series.application.find;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud de búsqueda de la serie")
public class SeriesFindRequest {

    @Schema(description = "Id del ejercicio al que pertenece la serie")
    private String ExerciseSeriesId;
}
