package n1b3lung0.apiGym.exercise_series.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotFound;

import java.io.Serial;

public final class ExerciseSeriesNotFound extends NotFound {
    @Serial
    private static final long serialVersionUID = -1L;

    public ExerciseSeriesNotFound(String id) {
        super(ExceptionConstants.EXERCISE_SERIES_NOT_FOUND, id);
    }
}
