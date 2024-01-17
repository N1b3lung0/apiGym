package n1b3lung0.apiGym.common.domain.criteria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Sorting implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final boolean sorted;

    private final String sortBy;

    private final SortDirection sortDirection;

    public static Sorting unsorted() {
        return new Sorting(false, null, null);
    }
    public static Sorting of(@NotBlank String sortBy, @NotNull SortDirection sortDirection) {
        return new Sorting(true, sortBy, sortDirection);
    }
}
