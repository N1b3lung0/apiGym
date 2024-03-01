package n1b3lung0.apiGym.series.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "series")
public final class Series implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id @GeneratedValue
    private final UUID id;

    @ManyToOne
    private final ExerciseSeries exerciseSeries;

    private final Integer serialNumber;

    private final Integer repetitionsToDo;

    private final Integer repetitionsDone;

    @Embedded
    private final AuditFields auditFields;

    public static Series create(
            ExerciseSeries exerciseSeries,
            Integer serialNumber,
            Integer repetitionsToDo,
            Integer repetitionsDone
    ) {
        return new Series(
                null,
                exerciseSeries,
                serialNumber,
                repetitionsToDo,
                repetitionsDone,
                new AuditFields()
        );
    }
}
