package n1b3lung0.apiGym.common.domain.exception;

import java.io.Serial;

public class AlreadyExists extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1L;

    public AlreadyExists(String message, String name) {
        super(String.format(message, name));
    }
}
