package n1b3lung0.apiGym.series.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.series.domain.Series;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Serie/s asociadas al ejercicio")
public class SeriesResponse {

    @Schema(description = "Identificador único de la serie")
    private String id;

    @Schema(description = "Identificador único del ejercicio")
    private ExerciseSeries exerciseSeries;

    @Schema(description = "Serie número X")
    private Integer serialNumber;

    @Schema(description = "Repeticiones a realizar")
    private Integer repetitionsToDo;

    @Schema(description = "Repeticiones realizadas")
    private Integer repetitionsDone;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de creación de la serie")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién creó la serie")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Fecha de actualización de la serie")
    private ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Quién actualizó la serie")
    private String updatedBy;

    public static SeriesResponse fromSeries(Series series) {
        return series != null ? new SeriesResponse(
                String.valueOf(series.getId()),
                series.getExerciseSeries(),
                series.getSerialNumber(),
                series.getRepetitionsToDo(),
                series.getRepetitionsDone(),
                series.getAuditFields().getCreatedAt(),
                series.getAuditFields().getCreatedBy(),
                series.getAuditFields().getUpdatedAt(),
                series.getAuditFields().getUpdatedBy()
        ) : new SeriesResponse();
    }
}
