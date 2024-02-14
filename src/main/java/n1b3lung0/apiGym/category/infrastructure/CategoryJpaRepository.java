package n1b3lung0.apiGym.category.infrastructure;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.CategorySearchCriteria;
import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.common.infrastructure.pagination.PaginationAndSorting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface CategoryJpaRepository extends CategoryRepository, JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    Optional<Category> findByIdAndDeletedFalse(UUID id);

    default Page<Category> find(CategorySearchCriteria criteria) {
        org.springframework.data.domain.Page<Category> page = findAll(
                CategorySpecification.byCriteria(criteria),
                PaginationAndSorting.asPageable(criteria.getPagination(), criteria.getSorting())
        );
        return new Page<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast());
    }
}
