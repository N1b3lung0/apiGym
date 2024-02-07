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
@Table(name = "workout")
public final class Workout implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id
    @GeneratedValue
    private final UUID id;

    @OneToMany(mappedBy = "workout")
    private final List<ExerciseSeries> exerciseSeries;

//    @ManyToOne
//    private final Trainee trainee; @OneToMany(mappedBy = "trainee")

    private final ZonedDateTime start;

    private final ZonedDateTime end;

}
