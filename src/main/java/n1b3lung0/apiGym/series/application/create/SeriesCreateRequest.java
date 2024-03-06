package n1b3lung0.apiGym.series.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.series.domain.Series;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Solicitud de creación de una serie")
public class SeriesCreateRequest {

    @Schema(description = "Ejercicio al que pertenece la serie")
    private ExerciseSeries exerciseSeries;

    @Schema(description = "Serie número X")
    private Integer serialNumber;

    @Schema(description = "Repeticiones a realizar")
    private Integer repetitionsToDo;

    @Schema(description = "Repeticiones realizadas")
    private Integer repetitionsDone;

    public Series toSeries(
            ExerciseSeries exerciseSeries,
            Integer serialNumber,
            Integer repetitionsToDo,
            Integer repetitionsDone
    ) {
        return Series.create(
                exerciseSeries,
                serialNumber,
                repetitionsToDo,
                repetitionsDone
        );
    }
}
