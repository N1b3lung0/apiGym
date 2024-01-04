package n1b3lung0.apiGym.exercise.application.find;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseFindRequest;
import n1b3lung0.apiGym.exercise.application.find.dto.ExerciseResponse;
import n1b3lung0.apiGym.exercise.application.find.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseFinder {

    private final ExerciseRepository repository;

    @Transactional(readOnly = true)
    public Exercise findById(String id) {
        return repository.findByIdAndDeletedFalse(UUIDUtils.fromString(id))
                .orElseThrow(() -> new ExerciseNotFound(id));
    }

    @Transactional(readOnly = true)
    public PageResponse<ExerciseResponse> find(ExerciseFindRequest request) {

        Page<Exercise> page = repository.find(request.toCriteria());

        return new PageResponse<>(
                page,
                page.getContent().stream().map(ExerciseResponse::fromExercise).collect(Collectors.toList())
        );
    }
}
