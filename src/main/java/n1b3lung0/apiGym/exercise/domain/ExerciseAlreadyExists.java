package n1b3lung0.apiGym.exercise.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExerciseAlreadyExists extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1;

    public ExerciseAlreadyExists(String message) {
        super(message);
    }
}
