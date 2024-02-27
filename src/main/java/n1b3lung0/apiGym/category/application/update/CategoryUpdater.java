package n1b3lung0.apiGym.category.application.update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.category.application.find.CategoryFinder;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryChange;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.exception.CategoryNotValid;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryUpdater {

    private final CategoryFinder finder;
    private final CategoryRepository repository;

    private static final String NAME_FIELD = "name";

    @Transactional
    public Category updateFields(CategoryUpdateRequest request) {
        List<CategoryChange> changes = new ArrayList<>();
        Category category = finder.findById(request.getId());

        String name = request.getName();
        if (StringUtils.isNotBlank(name) && !StringUtils.equals(name, category.getName())) {
            if (repository.findByName(name).isPresent()) {
                throw new CategoryNotValid(name, String.format(ExceptionConstants.CATEGORY_REPEATED, name));
            }
            category = category.withName(name);
            changes.add(CategoryChange.create(NAME_FIELD, category.getName()));
        }

        if (CollectionUtils.isNotEmpty(changes)) {
            category = repository.save(category);
            log.debug(String.format(LogConstants.CATEGORY_UPDATED, category));
        }

        return category;
    }
}
