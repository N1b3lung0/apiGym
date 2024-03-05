package n1b3lung0.apiGym.workout.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "workouts")
public final class Workout implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id @GeneratedValue
    private final UUID id;

    @OneToMany(mappedBy = "workout")
    private final List<ExerciseSeries> exerciseSeries;

    //@ManyToOne
    //private final Trainee trainee; @OneToMany(mappedBy = "trainee")

    private final ZonedDateTime startWorkout;

    private final ZonedDateTime endWorkout;

    @Embedded
    private final AuditFields auditFields;

    public static Workout create(
            List<ExerciseSeries> exerciseSeries,
            ZonedDateTime startWorkout,
            ZonedDateTime endWorkout
    ) {
        return new Workout(
                null,
                exerciseSeries,
                startWorkout,
                endWorkout,
                new AuditFields()
        );
    }
}
