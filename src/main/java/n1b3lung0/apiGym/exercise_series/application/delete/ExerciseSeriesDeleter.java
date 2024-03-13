package n1b3lung0.apiGym.exercise_series.application.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.exercise_series.application.find.ExerciseSeriesFinder;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseSeriesDeleter {

    private final ExerciseSeriesFinder finder;
    private final ExerciseSeriesRepository repository;

    @Transactional
    public void delete(String id) {
        ExerciseSeries exerciseSeries = finder.findById(id);
        repository.deleteById(UUIDUtils.fromString(id));
        log.debug(LogConstants.EXERCISE_SERIES_DELETED, exerciseSeries);
    }
}
