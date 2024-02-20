package n1b3lung0.apiGym.category.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.NotValid;

import java.io.Serial;

public class CategoryNotValid extends NotValid {

    @Serial
    private static final long serialVersionUID = -1L;

    public CategoryNotValid(String name, String reason) {
        super(ExceptionConstants.CATEGORY_NOT_VALID, name, reason);
    }
}
