package n1b3lung0.apiGym.series.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotFound;

import java.io.Serial;

public final class SeriesNotFound extends NotFound {

    @Serial
    private static final long serialVersionUID = -1L;

    public SeriesNotFound(String id) {
        super(ExceptionConstants.SERIES_NOT_FOUND, id);
    }
}
