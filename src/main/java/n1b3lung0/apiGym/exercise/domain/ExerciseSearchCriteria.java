package n1b3lung0.apiGym.exercise.domain;

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
public class ExerciseSearchCriteria extends Criteria implements Serializable {

    @Serial
    private static final long serialVersionUID = 7842844170926653636L;

    private final String query;

    public ExerciseSearchCriteria(Pagination pagination, Sorting sorting) {
        super(pagination, sorting);
        this.query = null;
    }

    public ExerciseSearchCriteria(Pagination pagination, Sorting sorting, String query) {
        super(pagination, sorting);
        this.query = query;
    }

    public static ExerciseSearchCriteria forLatest() {
        Pagination pagination = Pagination.of(0, 1);
        Sorting sorting = Sorting.of("auditFields.createdAt", SortDirection.DESC);
        return new ExerciseSearchCriteria(pagination, sorting);
    }
}
