package n1b3lung0.apiGym.exercise.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "category")
public final class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id
    @GeneratedValue
    private final UUID id;

    private final String name;

    @ManyToMany
    private final List<Exercise> exercises;
}
