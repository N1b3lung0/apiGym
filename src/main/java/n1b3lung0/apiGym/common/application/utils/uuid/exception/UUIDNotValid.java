package n1b3lung0.apiGym.common.application.utils.uuid.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotValid;

public final class UUIDNotValid extends NotValid {
    public UUIDNotValid(String id) {
        super(ExceptionConstants.UUID_NOT_VALID, id);
    }
}
