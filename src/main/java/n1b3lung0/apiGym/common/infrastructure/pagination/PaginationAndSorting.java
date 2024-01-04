package n1b3lung0.apiGym.common.infrastructure.pagination;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.common.domain.criteria.Pagination;
import n1b3lung0.apiGym.common.domain.criteria.Sorting;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public final class PaginationAndSorting {

    public static Pageable asPageable(Pagination pagination, Sorting sorting) {
        Sort sort = sorting.isSorted()
                ? Sort.by(Sort.Order.by(sorting.getSortBy()).with(Sort.Direction.fromString(sorting.getSortDirection().value())))
                : Sort.unsorted();

        if (pagination.isPaginated()) {
            return sorting.isSorted()
                    ? PageRequest.of(pagination.getPageNum(), pagination.getPageSize(), sort)
                    : PageRequest.of(pagination.getPageNum(), pagination.getPageSize());
        } else {
            return sorting.isSorted()
                    ? PageRequest.of(0, Integer.MAX_VALUE, sort)
                    : Pageable.unpaged();
        }
    }
}
