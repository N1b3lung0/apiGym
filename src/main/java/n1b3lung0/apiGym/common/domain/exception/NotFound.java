package n1b3lung0.apiGym.common.domain.exception;

public class NotFound extends RuntimeException {

    public NotFound(String message, String id) {
        super(String.format(message, id));
    }
}
