package n1b3lung0.apiGym.exercise.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotFound;

import java.io.Serial;

public final class ExerciseNotFound extends NotFound {
    @Serial
    private static final long serialVersionUID = -1L;

    public ExerciseNotFound(String id) {
        super(ExceptionConstants.EXERCISE_NOT_FOUND, id);
    }
}
