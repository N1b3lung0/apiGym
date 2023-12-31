package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data @Builder @RequiredArgsConstructor @NoArgsConstructor(force = true)
@Table(name = "exercises")
public final class Exercise implements Serializable {

    @Serial
    private static final long serialVersionUID = -3850311608773014615L;

    @Id @GeneratedValue
    private final UUID id;

    @With
    @Column(name = "name", nullable = false, unique = true)
    private final String name;

    @With
    @Column(name = "description")
    private final String description;

    @With
    @Column(name = "image")
    private final String image;

    @With
    @Column(name = "video")
    private final String video;

    @With
    @Column(name = "rest_time")
    private final String restTime;

    @With
    @Column(name = "deleted")
    private final boolean deleted;

    @Embedded
    private final AuditFields auditFields;

    public static Exercise create(
            String name,
            String description,
            String image,
            String video,
            String restTime
    ) {
        return new Exercise(
                null,
                name,
                description,
                image,
                video,
                restTime,
                Boolean.FALSE,
                new AuditFields()
        );
    }
}
