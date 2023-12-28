package n1b3lung0.apiGym.exercise.rest;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.rest.BaseRestController;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController extends BaseRestController {

    private final ExerciseFinder finder;

    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(ExerciseResponse.fromExercise(finder.findById(id)));
    }
}
