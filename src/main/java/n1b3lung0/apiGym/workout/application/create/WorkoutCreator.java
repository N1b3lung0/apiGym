package n1b3lung0.apiGym.workout.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutCreator {

    private final WorkoutRepository repository;

    @Transactional
    public Workout create(WorkoutCreateRequest request) {

        Workout created = repository.save(request.toWorkout(
                request.getExerciseSeries(),
                request.getStartWorkout(),
                request.getEndWorkout()
                )
        );

        log.debug(String.format(LogConstants.WORKOUT_CREATED, created));

        return created;
    }
}
