package n1b3lung0.apiGym.common.infrastructure.pagination;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.common.domain.criteria.Pagination;
import n1b3lung0.apiGym.common.domain.criteria.SortDirection;
import n1b3lung0.apiGym.common.domain.criteria.Sorting;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationAndSortingTests extends BaseIntegrationTest {

    @Test
    void shouldMapToPageable() {

        var pagination = Pagination.of(0, 10);
        var sorting = Sorting.of("date", SortDirection.DESC);
        var expected = PageRequest.of(
                pagination.getPageNum(),
                pagination.getPageSize(),
                Sort.by(Sort.Order.by(sorting.getSortBy()).with(Sort.Direction.fromString(sorting.getSortDirection().value())))
        );

        var actual = PaginationAndSorting.asPageable(pagination, sorting);

        assertEquals(expected, actual);
    }

    @Test
    void shouldMapToPageableIfUnpaged() {

        var sorting = Sorting.of("date", SortDirection.DESC);
        var expected = PageRequest.of(
                0,
                Integer.MAX_VALUE,
                Sort.by(Sort.Order.by(sorting.getSortBy()).with(Sort.Direction.fromString(sorting.getSortDirection().value())))
        );

        var actual = PaginationAndSorting.asPageable(Pagination.unpaged(), sorting);

        assertEquals(expected, actual);
    }

    @Test
    void shouldMapToPageableIfUnsorted() {

        var pagination = Pagination.of(0, 10);
        var expected = PageRequest.of(
                pagination.getPageNum(),
                pagination.getPageSize()
        );

        var actual = PaginationAndSorting.asPageable(pagination, Sorting.unsorted());

        assertEquals(expected, actual);
    }

    @Test
    void shouldMapToPageableIfUnpagedAndUnsorted() {

        var expected = Pageable.unpaged();

        var actual = PaginationAndSorting.asPageable(Pagination.unpaged(), Sorting.unsorted());

        assertEquals(expected, actual);
    }
}
