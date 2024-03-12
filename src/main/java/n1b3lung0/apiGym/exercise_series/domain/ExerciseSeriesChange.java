package n1b3lung0.apiGym.exercise_series.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data @Builder @RequiredArgsConstructor @NoArgsConstructor(force = true)
public final class ExerciseSeriesChange implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;
    private final String field;
    private final String value;

    public static ExerciseSeriesChange create(String field, String value) {
        return new ExerciseSeriesChange(field, value);
    }
}
