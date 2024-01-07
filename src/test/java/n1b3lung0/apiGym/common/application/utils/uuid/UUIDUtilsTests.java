package n1b3lung0.apiGym.common.application.utils.uuid;

import n1b3lung0.apiGym.common.BaseUnitTest;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.exception.UUIDNotValid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UUIDUtilsTests extends BaseUnitTest {

    @Test
    void shouldParseUUIDFromString() {
        String id = "83dd97c2-64af-4a82-b8af-ffa3d00f51ce";
        assertNotNull(UUIDUtils.fromString(id));
    }

    @Test
    void shouldFailParseUUIDFromStringIfNullId() {
        assertThrows(IllegalArgumentException.class, () -> UUIDUtils.fromString(null));
    }

    @Test
    void shouldFailParseUUIDFromStringIfNotValid() {
        String id = "wrong";
        Exception e = assertThrows(UUIDNotValid.class, () -> UUIDUtils.fromString(id));
        assertEquals(String.format(ExceptionConstants.UUID_NOT_VALID, id), e.getMessage());
    }
}
