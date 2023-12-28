package n1b3lung0.apiGym.exercise.application.find.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotFound;

public final class ExerciseNotFound extends NotFound {
    public ExerciseNotFound(String id) {
        super(ExceptionConstants.EXERCISE_NOT_FOUND, id);
    }
}
