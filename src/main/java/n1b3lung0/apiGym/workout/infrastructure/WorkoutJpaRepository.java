package n1b3lung0.apiGym.workout.infrastructure;

import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.common.infrastructure.pagination.PaginationAndSorting;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import n1b3lung0.apiGym.workout.domain.WorkoutSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WorkoutJpaRepository extends WorkoutRepository, JpaRepository<Workout, UUID>, JpaSpecificationExecutor<Workout> {
    default Page<Workout> find(WorkoutSearchCriteria criteria) {
        org.springframework.data.domain.Page<Workout> page = findAll(
                WorkoutSpecification.byCriteria(criteria),
                PaginationAndSorting.asPageable(criteria.getPagination(), criteria.getSorting())
        );
        return new Page<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast());
    }
}
