package n1b3lung0.apiGym.exercise_series.rest;

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
import n1b3lung0.apiGym.exercise_series.application.create.ExerciseSeriesCreateRequest;
import n1b3lung0.apiGym.exercise_series.application.create.ExerciseSeriesCreator;
import n1b3lung0.apiGym.exercise_series.application.delete.ExerciseSeriesDeleter;
import n1b3lung0.apiGym.exercise_series.application.find.ExerciseSeriesFindRequest;
import n1b3lung0.apiGym.exercise_series.application.find.ExerciseSeriesFinder;
import n1b3lung0.apiGym.exercise_series.application.find.ExerciseSeriesResponse;
import n1b3lung0.apiGym.exercise_series.application.update.ExerciseSeriesUpdateRequest;
import n1b3lung0.apiGym.exercise_series.application.update.ExerciseSeriesUpdater;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
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
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exercise-series")
@Tag(name = "Exercise Series", description = "Endpoints to perform operations with exercise series")
public class ExerciseSeriesController extends BaseRestController {

    private final ExerciseSeriesFinder finder;
    private final ExerciseSeriesCreator creator;
    private final ExerciseSeriesUpdater updater;
    private final ExerciseSeriesDeleter deleter;

    private static final String FIND_BY_ID = "Find an exercise series by its unique id";
    private static final String FIND_BY_CRITERIA = "Get a filtered, sorted and paginated list of exercises series";
    private static final String CREATE = "Create an exercise series";
    private static final String UPDATE = "Update a given exercise series";
    private static final String DELETE = "Delete a given exercise series";

    @OkRes @NotFoundRes @SecurityRes
    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_ID, description = FIND_BY_ID)
    public ResponseEntity<ExerciseSeriesResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(ExerciseSeriesResponse.fromExerciseSeries(finder.findById(id)));
    }

    @OkRes @SecurityRes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = FIND_BY_CRITERIA, description = FIND_BY_CRITERIA)
    public ResponseEntity<PageResponse<ExerciseSeriesResponse>> findByCriteria(@Valid ExerciseSeriesFindRequest request) {
        return ResponseEntity.ok(finder.find(request));
    }

    @CreatedRes @SecurityRes
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = CREATE, description = CREATE)
    public ResponseEntity<Void> create(@RequestBody @Valid ExerciseSeriesCreateRequest request) throws URISyntaxException {
        ExerciseSeries saved = creator.create(request);
        URI location = UriComponentsBuilder.fromPath("/exercise-series/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @OkRes @NotFoundRes @SecurityRes
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = UPDATE, description = UPDATE)
    public ResponseEntity<ExerciseSeriesResponse> update(@RequestBody @Valid ExerciseSeriesUpdateRequest request) {
        return ResponseEntity.ok().build(); // TODO: ResponseEntity.ok(ExerciseSeriesResponse.fromExerciseSeries(updater.updateFields(request)));
    }

    @NoContentRes @NotFoundRes @SecurityRes
    @DeleteMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE, description = DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        // TODO: deleter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
