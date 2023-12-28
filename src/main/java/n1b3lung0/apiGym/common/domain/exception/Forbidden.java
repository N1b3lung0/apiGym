package n1b3lung0.apiGym.common.domain.exception;

public class Forbidden extends RuntimeException {

    public Forbidden(String reason) {
        super(reason);
    }
}
