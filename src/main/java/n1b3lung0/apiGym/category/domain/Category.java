package n1b3lung0.apiGym.category.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.exercise.domain.Exercise;

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
@Table(name = "categories")
public final class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @With
    @Id @GeneratedValue
    private final UUID id;

    @With
    @Column(name = "name", nullable = false, unique = true)
    private final String name;

    @ManyToMany(mappedBy = "categories")
    private final Set<Exercise> exercises;

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted")
    private final boolean deleted;

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted_at")
    private final ZonedDateTime deletedAt;

    @With(AccessLevel.PRIVATE)
    @Column(name = "deleted_by")
    private final String deletedBy;

    @Embedded
    private final AuditFields auditFields;

    public static Category create(
            String name
    ) {
        return new Category(
                null,
                name,
                new HashSet<>(),
                Boolean.FALSE,
                null,
                null,
                new AuditFields()
        );
    }

    public Category delete() {
        return withDeleted(Boolean.TRUE)
                .withDeletedAt(ZonedDateTime.now())
                .withDeletedBy("n1b3lung0");
    }
}
