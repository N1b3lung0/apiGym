package n1b3lung0.apiGym.category.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.category.application.create.CategoryCreateRequest;
import n1b3lung0.apiGym.category.application.create.CategoryCreator;
import n1b3lung0.apiGym.category.application.delete.CategoryDeleter;
import n1b3lung0.apiGym.category.application.find.CategoryFindRequest;
import n1b3lung0.apiGym.category.application.find.CategoryFinder;
import n1b3lung0.apiGym.category.application.find.CategoryResponse;
import n1b3lung0.apiGym.category.application.update.CategoryUpdateRequest;
import n1b3lung0.apiGym.category.application.update.CategoryUpdater;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.rest.BaseRestController;
import n1b3lung0.apiGym.common.rest.swagger.CreatedRes;
import n1b3lung0.apiGym.common.rest.swagger.NoContentRes;
import n1b3lung0.apiGym.common.rest.swagger.NotFoundRes;
import n1b3lung0.apiGym.common.rest.swagger.OkRes;
import n1b3lung0.apiGym.common.rest.swagger.SecurityRes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Endpoints to perform operations with categories")
public class CategoryController extends BaseRestController {

    private final CategoryFinder finder;
    private final CategoryCreator creator;
    private final CategoryUpdater updater;
    private final CategoryDeleter deleter;

    private static final String FIND_BY_ID = "Find a category by its unique id";
    private static final String FIND_BY_CRITERIA = "Get a filtered, sorted and paginated list of categories";
    private static final String CREATE = "Create a category";
    private static final String UPDATE = "Update a given category";
    private static final String DELETE = "Delete a given category";

    @OkRes @NotFoundRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_ID, description = FIND_BY_ID)
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(CategoryResponse.fromCategory(finder.findById(id)));
    }
    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_CRITERIA, description = FIND_BY_CRITERIA)
    public ResponseEntity<PageResponse<CategoryResponse>> findByCriteria(@Valid CategoryFindRequest request) {
        return ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = CREATE, description = CREATE)
    public ResponseEntity<Void> create(@RequestBody @Valid CategoryCreateRequest request) {
        Category saved = creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/categories/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @OkRes @NotFoundRes @SecurityRes
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = UPDATE, description = UPDATE)
    public ResponseEntity<CategoryResponse> update(@RequestBody @Valid CategoryUpdateRequest request) {
        return ResponseEntity.ok(CategoryResponse.fromCategory(updater.updateFields(request)));
    }

    @NoContentRes @NotFoundRes @SecurityRes
    @DeleteMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE, description = DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
