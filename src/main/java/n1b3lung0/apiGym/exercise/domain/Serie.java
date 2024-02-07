package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "series")
public final class Serie implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id
    @GeneratedValue
    private final UUID id;

    @ManyToOne
    private final ExerciseSeries exerciseSeries;

    private final Integer serialNumber;

    private final Integer repetitionsToDo;

    private final Integer repetitionsDone;
}
