package n1b3lung0.apiGym.series.domain;

import java.util.Optional;
import java.util.UUID;

public interface SeriesRepository {
    Optional<Series> findById(UUID id);
    Series save(Series series);

    void deleteById(UUID id);

}
