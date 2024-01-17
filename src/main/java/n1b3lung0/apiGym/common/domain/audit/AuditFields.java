package n1b3lung0.apiGym.common.domain.audit;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Embeddable
public final class AuditFields implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @EqualsAndHashCode.Exclude
    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Column(name = "created_by")
    private final String createdBy;

    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at")
    private final LocalDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Column(name = "updated_by")
    private final String updatedBy;


}
