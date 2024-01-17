package n1b3lung0.apiGym.common.domain.exception;

import java.io.Serial;

public class NotAllowed extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1L;

    public NotAllowed(String message) {
        super(message);
    }
}
