package n1b3lung0.apiGym.workout.rest;

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
import n1b3lung0.apiGym.workout.application.create.WorkoutCreateRequest;
import n1b3lung0.apiGym.workout.application.create.WorkoutCreator;
import n1b3lung0.apiGym.workout.application.delete.WorkoutDeleter;
import n1b3lung0.apiGym.workout.application.find.WorkoutFindRequest;
import n1b3lung0.apiGym.workout.application.find.WorkoutFinder;
import n1b3lung0.apiGym.workout.application.find.WorkoutResponse;
import n1b3lung0.apiGym.workout.application.update.WorkoutUpdateRequest;
import n1b3lung0.apiGym.workout.application.update.WorkoutUpdater;
import n1b3lung0.apiGym.workout.domain.Workout;
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
@RequestMapping("/workouts")
@Tag(name = "Workouts", description = "Endpoints to perform operations with workouts")
public class WorkoutController extends BaseRestController {

    private final WorkoutFinder finder;
    private final WorkoutCreator creator;
    private final WorkoutUpdater updater;
    private final WorkoutDeleter deleter;

    private static final String FIND_BY_ID = "Find a workout by its unique id";
    private static final String FIND_BY_CRITERIA = "Get a filtered, sorted and paginated list of workouts";
    private static final String CREATE = "Create a workout";
    private static final String UPDATE = "Update a given workout";
    private static final String DELETE = "Delete a given workout";

    @OkRes @NotFoundRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_ID, description = FIND_BY_ID)
    public ResponseEntity<WorkoutResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().build(); // TODO: ResponseEntity.ok(WorkoutResponse.fromWorkout(finder.findById(id)));
    }

    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_CRITERIA, description = FIND_BY_CRITERIA)
    public ResponseEntity<PageResponse<WorkoutResponse>> findByCriteria(@Valid WorkoutFindRequest request) {
        return ResponseEntity.ok().build(); // TODO: ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = CREATE, description = CREATE)
    public ResponseEntity<Void> create(@RequestBody @Valid WorkoutCreateRequest request) {
        Workout saved = Workout.builder().build(); // TODO: creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/workouts/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @OkRes @NotFoundRes @SecurityRes
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = UPDATE, description = UPDATE)
    public ResponseEntity<WorkoutResponse> update(@RequestBody @Valid WorkoutUpdateRequest request) {
        return ResponseEntity.ok().build(); // TODO: ResponseEntity.ok(WorkoutResponse.fromWorkout(updater.updateFields(request)));
    }

    @NoContentRes @NotFoundRes @SecurityRes
    @DeleteMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE, description = DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        // TODO: deleter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
