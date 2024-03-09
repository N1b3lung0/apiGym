package n1b3lung0.apiGym.workout.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotFound;

import java.io.Serial;

public final class WorkoutNotFound extends NotFound {

    @Serial
    private static final long serialVersionUID = -1L;

    public WorkoutNotFound(String id) {
        super(ExceptionConstants.WORKOUT_NOT_FOUND, id);
    }
}
