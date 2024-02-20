package n1b3lung0.apiGym.category.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.category.application.create.CategoryCreateRequest;
import n1b3lung0.apiGym.category.application.create.CategoryCreator;
import n1b3lung0.apiGym.category.application.find.CategoryFindRequest;
import n1b3lung0.apiGym.category.application.find.CategoryFinder;
import n1b3lung0.apiGym.category.application.find.CategoryResponse;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.rest.BaseRestController;
import n1b3lung0.apiGym.common.rest.swagger.CreatedRes;
import n1b3lung0.apiGym.common.rest.swagger.OkRes;
import n1b3lung0.apiGym.common.rest.swagger.SecurityRes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Endpoints to perform operations with categories")
public class CategoryController extends BaseRestController {

    private final CategoryFinder finder;
    private final CategoryCreator creator;

    @OkRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find a category by its unique id", description = "Find a category by its unique id")
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(CategoryResponse.fromCategory(finder.findById(id)));
    }
    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a filtered, sorted and paginated list of categories", description = "Get a filtered, sorted and paginated list of categories")
    public ResponseEntity<PageResponse<CategoryResponse>> findByCriteria(@Valid CategoryFindRequest request) {
        return ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a category", description = "Create a category")
    public ResponseEntity<Void> create(@RequestBody @Valid CategoryCreateRequest request) {
        Category saved = creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/api/categories/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
