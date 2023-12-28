package n1b3lung0.apiGym.common.domain.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class NotValid extends RuntimeException {

    private final List<String> errors;

    public NotValid(String message, Object... args) {
        super(String.format(message, args));
        this.errors = Collections.singletonList(String.format(message, args));
    }
}
