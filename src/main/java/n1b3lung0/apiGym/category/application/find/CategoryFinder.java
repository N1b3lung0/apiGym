package n1b3lung0.apiGym.category.application.find;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.exception.CategoryNotFound;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.common.domain.criteria.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryFinder {

    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public Category findById(String id) {
        return repository.findByIdAndDeletedFalse(UUIDUtils.fromString(id))
                .orElseThrow(() -> new CategoryNotFound(id));
    }

    @Transactional(readOnly = true)
    public PageResponse<CategoryResponse> find(CategoryFindRequest request) {
        Page<Category> page = repository.find(request.toCriteria());

        return new PageResponse<>(
                page,
                page.getContent().stream()
                        .map(CategoryResponse::fromCategory)
                        .collect(Collectors.toList())
        );
    }
}
