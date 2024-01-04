package n1b3lung0.apiGym.exercise.infrastructure;

import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.common.infrastructure.pagination.PaginationAndSorting;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.ExerciseSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseJpaRepository extends ExerciseRepository, JpaRepository<Exercise, UUID>, JpaSpecificationExecutor<Exercise> {

    Optional<Exercise> findByIdAndDeletedFalse(UUID id);

    default Page<Exercise> find(ExerciseSearchCriteria criteria) {
        org.springframework.data.domain.Page<Exercise> page = findAll(
                ExerciseSpecification.byCriteria(criteria),
                PaginationAndSorting.asPageable(criteria.getPagination(), criteria.getSorting())
        );
        return new Page<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast());
    }
}
