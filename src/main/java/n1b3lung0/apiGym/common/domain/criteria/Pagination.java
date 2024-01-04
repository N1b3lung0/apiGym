package n1b3lung0.apiGym.common.domain.criteria;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Pagination implements Serializable {

    @Serial
    private static final long serialVersionUID = -7000872155707972340L;

    private final boolean paginated;
    private final Integer pageNum;
    private final Integer pageSize;

    public static Pagination unpaged() {
        return new Pagination(false, null, null);
    }

    public static Pagination of(int pageNum, int pageSize) {
        return new Pagination(true, pageNum, pageSize);
    }
}
