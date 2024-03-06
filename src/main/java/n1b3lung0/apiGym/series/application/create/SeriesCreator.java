package n1b3lung0.apiGym.series.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.series.domain.SeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeriesCreator {

    private final SeriesRepository repository;

    @Transactional
    public Series create(SeriesCreateRequest request) {

        Series created = repository.save(request.toSeries(
                request.getExerciseSeries(),
                request.getSerialNumber(),
                request.getRepetitionsToDo(),
                request.getRepetitionsDone()
        ));

        log.debug(String.format(LogConstants.SERIES_CREATED, created));
        return created;
    }
}
