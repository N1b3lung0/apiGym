package n1b3lung0.apiGym.exercise.application.create.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotValid;

import java.io.Serial;

public final class ExerciseNotValid extends NotValid {

    @Serial
    private static final long serialVersionUID = 6577519326880524401L;

    public ExerciseNotValid(String reason) {
        super(ExceptionConstants.EXERCISE_NOT_VALID, reason);
    }
}
