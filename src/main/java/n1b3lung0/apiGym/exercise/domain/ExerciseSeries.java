package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.*;
import lombok.*;

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
    @Id
    @GeneratedValue
    private final UUID id;

    @ManyToOne
    private final Workout workout;

    @OneToOne
    private final Exercise exercise;

    @OneToMany(mappedBy = "exerciseSeries")
    private final List<Serie> series;

    // TODO: Meter aquí el weight

    private final ZonedDateTime start;

    private final ZonedDateTime end;
}
