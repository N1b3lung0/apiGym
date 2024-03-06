package n1b3lung0.apiGym.series.application.delete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.series.application.find.SeriesFinder;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.series.domain.SeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeriesDeleter {

    private final SeriesFinder finder;
    private final SeriesRepository repository;

    @Transactional
    public void delete(String id) {
        Series series = finder.findById(id);
        repository.deleteById(UUIDUtils.fromString(id));
        log.debug(String.format(LogConstants.SERIES_DELETED, series));
    }
}
