package n1b3lung0.apiGym.workout.application.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.workout.application.find.WorkoutFinder;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutDeleter {

    private final WorkoutFinder finder;
    private final WorkoutRepository repository;

    @Transactional
    public void delete(String id) {
        Workout workout = finder.findById(id);
        repository.deleteById(UUID.fromString(id));
        log.debug(LogConstants.WORKOUT_DELETED, workout);
    }
}
