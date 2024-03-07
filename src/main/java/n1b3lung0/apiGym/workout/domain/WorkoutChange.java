package n1b3lung0.apiGym.workout.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public final class WorkoutChange implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final String field;

    private final Object value;

    public static WorkoutChange create(String field, Object value) {
        return new WorkoutChange(field, value);
    }
}
