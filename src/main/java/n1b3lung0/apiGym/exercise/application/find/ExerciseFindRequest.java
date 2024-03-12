package n1b3lung0.apiGym.exercise.application.find;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.domain.criteria.Pagination;
import n1b3lung0.apiGym.common.domain.criteria.SortDirection;
import n1b3lung0.apiGym.common.domain.criteria.Sorting;
import n1b3lung0.apiGym.exercise.domain.ExerciseSearchCriteria;
import org.apache.commons.lang3.StringUtils;

@Data @NoArgsConstructor @AllArgsConstructor
@Schema(description = "Exercise find request")
public class ExerciseFindRequest {

    @Schema(description = "Search term (in name and description)")
    private String query;

    @Schema(description = "Results page (empty to disable pagination)")
    private Integer page;

    @Schema(description = "Page size (empty to disable pagination)")
    private Integer size;

    @Schema(description = "Sort by (createdAt by default)")
    private String sortBy;

    @Schema(description = "Sort direction (ASC, DESC). DESC by default")
    private SortDirection sortDirection;

    public ExerciseSearchCriteria toCriteria() {
        Pagination pagination = page != null && size != null
                ? Pagination.of(page, size)
                : Pagination.unpaged();
        Sorting sorting = StringUtils.isNotBlank(sortBy) && sortDirection != null
                ? Sorting.of(sortBy, sortDirection)
                : Sorting.of("auditFields.createdAt", SortDirection.DESC);
        return new ExerciseSearchCriteria(pagination, sorting, query);
    }
}
