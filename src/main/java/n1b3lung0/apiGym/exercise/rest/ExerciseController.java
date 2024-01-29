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
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreator;
import n1b3lung0.apiGym.exercise.application.create.ExerciseCreateRequest;
import n1b3lung0.apiGym.exercise.application.delete.ExerciseDeleter;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFindRequest;
import n1b3lung0.apiGym.exercise.application.find.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdater;
import n1b3lung0.apiGym.exercise.application.update.ExerciseUpdateRequest;
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
@RequestMapping("/api/exercises")
@Tag(name = "Exercises", description = "Endpoints to perform operations with exercises")
public class ExerciseController extends BaseRestController {

    private final ExerciseFinder finder;
    private final ExerciseCreator creator;
    private final ExerciseUpdater updater;
    private final ExerciseDeleter deleter;

    @OkRes @NotFoundRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find an exercise by its unique id", description = "Find an exercise by its unique id")
    public ResponseEntity<ExerciseResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(ExerciseResponse.fromExercise(finder.findById(id)));
    }

    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a filtered, sorted and paginated list of exercises", description = "Get a filtered, sorted and paginated list of exercises")
    public ResponseEntity<PageResponse<ExerciseResponse>> findByCriteria(@Valid ExerciseFindRequest request) {
        return ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create an exercise", description = "Create an exercise")
    public ResponseEntity<Void> create(@RequestBody @Valid ExerciseCreateRequest request) {
        Exercise saved = creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/api/exercises/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @OkRes @NotFoundRes @SecurityRes
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a given exercise", description = "Update a given exercise")
    public ResponseEntity<ExerciseResponse> update(@Valid @RequestBody ExerciseUpdateRequest request) {
        return ResponseEntity.ok(ExerciseResponse.fromExercise(updater.updateFields(request)));
    }

    @NoContentRes @NotFoundRes @SecurityRes
    @DeleteMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a given exercise", description = "Delete a given exercise")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
