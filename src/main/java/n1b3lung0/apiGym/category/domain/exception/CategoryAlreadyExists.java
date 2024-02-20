package n1b3lung0.apiGym.category.domain.exception;

import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.AlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExists extends AlreadyExists {

    @Serial
    private static final long serialVersionUID = -1L;

    public CategoryAlreadyExists(String name) {
        super(ExceptionConstants.CATEGORY_REPEATED, name);
    }
}
