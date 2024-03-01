package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.With;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.domain.vo.Image;
import n1b3lung0.apiGym.common.domain.vo.Video;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "exercises")
public final class Exercise implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id @GeneratedValue
    private final UUID id;

    @With
    @Column(name = "name", nullable = false, unique = true)
    private final String name;

    @With
    @Column(name = "description")
    private final String description;

    @With
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "image_name")),
            @AttributeOverride(name = "description", column = @Column(name = "image_description")),
            @AttributeOverride(name = "url", column = @Column(name = "image_url"))
    })
    private final Image image;

    @With
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "video_name")),
            @AttributeOverride(name = "description", column = @Column(name = "video_description")),
            @AttributeOverride(name = "url", column = @Column(name = "video_url"))
    })
    private final Video video;

    @With
    @Column(name = "intensity")
    private final Integer intensity;

    @With
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "exercises_categories",
        joinColumns = {@JoinColumn(name = "exercise_id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private final Set<Category> categories;

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted")
    private final boolean deleted; // TODO: Cambiar por active y filtrar que no salgan los active= false

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted_at")
    private final ZonedDateTime deletedAt;

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted_by")
    private final String deletedBy;

    @Embedded
    private final AuditFields auditFields;

    public static Exercise create(
            String name,
            String description,
            Image image,
            Video video,
            Integer intensity
    ) {
        return new Exercise(
                null,
                name,
                description,
                image,
                video,
                intensity,
                new HashSet<>(),
                Boolean.FALSE,
                null,
                null,
                new AuditFields()
        );
    }

    public Exercise delete() {
        return withDeleted(Boolean.TRUE)
                .withDeletedAt(ZonedDateTime.now())
                .withDeletedBy("n1b3lung0");
    }
}
