package n1b3lung0.apiGym.exercise.domain;

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
public class ExerciseChange implements Serializable {

    @Serial
    private static final long serialVersionUID = 5170782781975934145L;

    private final String field;

    private final Object value;

    public static ExerciseChange create(String field, Object value) {
        return new ExerciseChange(field, value);
    }
}
