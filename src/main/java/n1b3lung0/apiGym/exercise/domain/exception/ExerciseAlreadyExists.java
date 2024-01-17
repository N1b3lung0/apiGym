package n1b3lung0.apiGym.exercise.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.AlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class ExerciseAlreadyExists extends AlreadyExists {

    @Serial
    private static final long serialVersionUID = -1L;

    public ExerciseAlreadyExists(String name) {
        super(ExceptionConstants.EXERCISE_REPEATED, name);
    }
}
