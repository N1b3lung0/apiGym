package n1b3lung0.apiGym.category.domain;

import n1b3lung0.apiGym.common.domain.criteria.Page;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Page<Category> find(CategorySearchCriteria criteria);

    Optional<Category> findByIdAndDeletedFalse(UUID id);

    Optional<Category> findByName(String name);

    Category save(Category category);
}
