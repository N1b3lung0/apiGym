package n1b3lung0.apiGym.exercise.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotValid;

import java.io.Serial;

public final class ExerciseNotValid extends NotValid {

    @Serial
    private static final long serialVersionUID = -749842304936762708L;

    public ExerciseNotValid(String name, String reason) {
        super(ExceptionConstants.EXERCISE_NOT_VALID, name, reason);
    }
}
