package n1b3lung0.apiGym.exercise.application.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseDeleter {

    private final ExerciseFinder finder;
    private final ExerciseRepository repository;

    @Transactional
    public void delete(String id) {
        Exercise exercise = finder.findById(id);
        Exercise deleted = exercise.delete();
        repository.save(deleted);
        log.debug(String.format(LogConstants.EXERCISE_DELETED, deleted));
    }
}
