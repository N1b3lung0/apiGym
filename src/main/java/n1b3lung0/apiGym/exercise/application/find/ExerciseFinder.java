package n1b3lung0.apiGym.exercise.application.find;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.exercise.application.find.exception.ExerciseNotFound;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExerciseFinder {

    private final ExerciseRepository repository;

    @Transactional(readOnly = true)
    public Exercise findById(String id) {
        return repository.findByIdAndDeletedFalse(UUIDUtils.fromString(id))
                .orElseThrow(() -> new ExerciseNotFound(id));
    }
}
