package n1b3lung0.apiGym.exercise_series.infrastructure;

import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.common.infrastructure.pagination.PaginationAndSorting;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesRepository;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExerciseSeriesJpaRepository extends ExerciseSeriesRepository, JpaRepository<ExerciseSeries, UUID>, JpaSpecificationExecutor<ExerciseSeries> {
    default Page<ExerciseSeries> find(ExerciseSeriesSearchCriteria criteria) {
        org.springframework.data.domain.Page<ExerciseSeries> page = findAll(
                ExerciseSeriesSpecification.byCriteria(criteria),
                PaginationAndSorting.asPageable(criteria.getPagination(), criteria.getSorting())
        );
        return new Page<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast());
    }
}
