package n1b3lung0.apiGym.exercise_series.domain;

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
public class ExerciseSeriesSearchCriteria extends Criteria implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final String query;

    public ExerciseSeriesSearchCriteria(Pagination pagination, Sorting sorting) {
        super(pagination, sorting);
        this.query = null;
    }

    public ExerciseSeriesSearchCriteria(Pagination pagination, Sorting sorting, String query) {
        super(pagination, sorting);
        this.query = query;
    }

    public static ExerciseSeriesSearchCriteria forLatest() {
        Pagination pagination = Pagination.of(0, 1);
        Sorting sorting = Sorting.of("auditFields.createdAt", SortDirection.DESC);
        return new ExerciseSeriesSearchCriteria(pagination, sorting);
    }
}
