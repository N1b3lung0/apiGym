package n1b3lung0.apiGym.category.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryChange implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final String field;

    private final Object value;

    public static CategoryChange create(String field, Object value) {
        return new CategoryChange(field, value);
    }
}
