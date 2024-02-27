package n1b3lung0.apiGym.category.application.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.category.application.find.CategoryFinder;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryDeleter {

    private final CategoryFinder finder;
    private final CategoryRepository repository;

    @Transactional
    public void delete(String id) {
        Category category = finder.findById(id);
        Category deleted = category.delete();
        repository.save(deleted);
        log.debug(String.format(LogConstants.CATEGORY_DELETED, deleted));
    }
}
