package n1b3lung0.apiGym.exercise.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.rest.BaseRestController;
import n1b3lung0.apiGym.common.rest.swagger.CreatedRes;
import n1b3lung0.apiGym.common.rest.swagger.NoContentRes;
import n1b3lung0.apiGym.common.rest.swagger.NotFoundRes;
import n1b3lung0.apiGym.common.rest.swagger.OkRes;
import n1b3lung0.apiGym.common.rest.swagger.SecurityRes;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreateRequest;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreator;
import n1b3lung0.apiGym.exercise.application.delete.ExerciseDeleter;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFindRequest;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdateRequest;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdater;
import n1b3lung0.apiGym.exercise.domain.Exercise;
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
@RequestMapping("/exercises")
@Tag(name = "Exercises", description = "Endpoints to perform operations with exercises")
public class ExerciseController extends BaseRestController {

    private final ExerciseFinder finder;
    private final ExerciseCreator creator;
    private final ExerciseUpdater updater;
    private final ExerciseDeleter deleter;

    private static final String FIND_BY_ID = "Find an exercise by its unique id";
    private static final String FIND_BY_CRITERIA = "Get a filtered, sorted and paginated list of exercises";
    private static final String CREATE = "Create an exercise";
    private static final String UPDATE = "Update a given exercise";
    private static final String DELETE = "Delete a given exercise";

    @OkRes @NotFoundRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_ID, description = FIND_BY_ID)
    public ResponseEntity<ExerciseResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(ExerciseResponse.fromExercise(finder.findById(id)));
    }

    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_CRITERIA, description = FIND_BY_CRITERIA)
    public ResponseEntity<PageResponse<ExerciseResponse>> findByCriteria(@Valid ExerciseFindRequest request) {
        return ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = CREATE, description = CREATE)
    public ResponseEntity<Void> create(@RequestBody @Valid ExerciseCreateRequest request) {
        Exercise saved = creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/exercises/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @OkRes @NotFoundRes @SecurityRes
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = UPDATE, description = UPDATE)
    public ResponseEntity<ExerciseResponse> update(@Valid @RequestBody ExerciseUpdateRequest request) {
        return ResponseEntity.ok(ExerciseResponse.fromExercise(updater.updateFields(request)));
    }

    @NoContentRes @NotFoundRes @SecurityRes
    @DeleteMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE, description = DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
