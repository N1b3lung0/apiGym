package n1b3lung0.apiGym.exercise.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.exception.ExerciseAlreadyExists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseCreator {

    private final ExerciseRepository repository;

    @Transactional
    public Exercise create(ExerciseCreateRequest request) {

        String name = request.getName();
        if(repository.findByName(name).isPresent()) {
            throw new ExerciseAlreadyExists(name);
        }

        Exercise exercise = request.toExercise(
                name,
                request.getDescription(),
                request.getImage(),
                request.getVideo(),
                request.getRestTime()
        );

        Exercise created = repository.save(exercise);

        log.debug(String.format(LogConstants.EXERCISE_CREATED, created));

        return created;
    }
}
