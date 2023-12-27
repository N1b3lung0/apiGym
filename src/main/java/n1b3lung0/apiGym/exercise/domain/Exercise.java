package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor(force = true)
@Table(name = "exercises")
public final class Exercise {

    @Id @GeneratedValue
    private final UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private final String name;

    @Column(name = "description")
    private final String description;

    @Column(name = "image")
    private final String image;

    @Column(name = "video")
    private final String video;

    @Column(name = "rest_time")
    private final Integer restTime;
}
