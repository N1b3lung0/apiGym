package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "exercise_series")
public final class ExerciseSeries implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id @GeneratedValue
    private final UUID id;

    @OneToOne
    private final Exercise exercise;

    @OneToMany(mappedBy = "exerciseSeries")
    private final List<Series> series;

    // TODO: Meter aquí el weight

    private final ZonedDateTime start;

    private final ZonedDateTime end;

    @Embedded
    private final AuditFields auditFields;

    public static ExerciseSeries create(
            Exercise exercise,
            List<Series> series,
            ZonedDateTime start,
            ZonedDateTime end
    ) {
        return new ExerciseSeries(
                null,
                exercise,
                series,
                start,
                end,
                new AuditFields()
        );
    }
}
