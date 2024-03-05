package n1b3lung0.apiGym.exercise_series.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.workout.domain.Workout;

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

    @ManyToOne
    private final Workout workout;

    @OneToOne
    private final Exercise exercise;

    @OneToMany(mappedBy = "exerciseSeries")
    private final List<Series> series;

    private final Float weight;

    @With
    @Column(name = "start_series")
    private final ZonedDateTime startSeries;

    @With
    @Column(name = "end_series")
    private final ZonedDateTime endSeries;

    @With
    @Column(name = "rest_time")
    private final RestTime restTime;

    @Embedded
    private final AuditFields auditFields;

    public static ExerciseSeries create(
            Workout workout,
            Exercise exercise,
            List<Series> series,
            Float weight,
            ZonedDateTime startSeries,
            ZonedDateTime endSeries,
            RestTime restTime
    ) {
        return new ExerciseSeries(
                null,
                workout,
                exercise,
                series,
                weight,
                startSeries,
                endSeries,
                restTime,
                new AuditFields()
        );
    }
}