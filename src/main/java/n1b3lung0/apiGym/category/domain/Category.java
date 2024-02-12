package n1b3lung0.apiGym.category.domain;

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

    private final String name;

    //@ManyToMany
    //private final List<Exercise> exercises;

    @Embedded
    private final AuditFields auditFields;

    public static Category create(
            String name
    ) {
        return new Category(
                null,
                name,
                new AuditFields()
        );
    }
}
