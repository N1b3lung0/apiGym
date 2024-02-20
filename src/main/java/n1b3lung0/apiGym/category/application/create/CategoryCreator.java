package n1b3lung0.apiGym.category.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.exception.CategoryAlreadyExists;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryCreator {

    private final CategoryRepository repository;

    @Transactional
    public Category create(CategoryCreateRequest request) {
        String name = request.getName();
        if (repository.findByName(name).isPresent()) {
            throw new CategoryAlreadyExists(name);
        }

        Category category = request.toCategory(name);
        Category created = repository.save(category);
        log.debug(String.format(LogConstants.CATEGORY_CREATED, created));

        return created;
    }
}
