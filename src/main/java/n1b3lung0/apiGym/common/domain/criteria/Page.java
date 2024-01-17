package n1b3lung0.apiGym.common.domain.criteria;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Page<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final List<T> content;
    private final int number;
    private final int size;
    private final boolean first;
    private final boolean last;

    public T getFirstItem() {
        return CollectionUtils.isNotEmpty(content) ? content.get(0) : null;
    }
}
