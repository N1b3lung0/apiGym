package n1b3lung0.apiGym.series.application.find;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.series.domain.SeriesRepository;
import n1b3lung0.apiGym.series.domain.exception.SeriesNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeriesFinder {

    private final SeriesRepository repository;

    @Transactional(readOnly = true)
    public Series findById(String id) {
        return repository.findById(UUIDUtils.fromString(id))
                .orElseThrow(() -> new SeriesNotFound(id));
    }

}
