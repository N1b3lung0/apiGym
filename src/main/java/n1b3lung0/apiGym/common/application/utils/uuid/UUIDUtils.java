package n1b3lung0.apiGym.common.application.utils.uuid;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.exception.UUIDNotValid;
import org.springframework.util.Assert;

import java.util.UUID;

@UtilityClass
public final class UUIDUtils {

    public static UUID fromString(String id) {
        Assert.notNull(id, ExceptionConstants.ID_REQUIRED);

        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new UUIDNotValid(id);
        }
    }
}
