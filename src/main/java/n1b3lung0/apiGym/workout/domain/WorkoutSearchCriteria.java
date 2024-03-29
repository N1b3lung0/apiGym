package n1b3lung0.apiGym.workout.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import n1b3lung0.apiGym.common.domain.criteria.Criteria;
import n1b3lung0.apiGym.common.domain.criteria.Pagination;
import n1b3lung0.apiGym.common.domain.criteria.SortDirection;
import n1b3lung0.apiGym.common.domain.criteria.Sorting;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class WorkoutSearchCriteria extends Criteria implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private final String query;

    public WorkoutSearchCriteria(Pagination pagination, Sorting sorting) {
        super(pagination, sorting);
        this.query = null;
    }

    public WorkoutSearchCriteria(Pagination pagination, Sorting sorting, String query) {
        super(pagination, sorting);
        this.query = query;
    }

    public static WorkoutSearchCriteria forLatest() {
        Pagination pagination = Pagination.of(0, 1);
        Sorting sorting = Sorting.of("auditFields.createdAt", SortDirection.DESC);
        return new WorkoutSearchCriteria(pagination, sorting);
    }
}
