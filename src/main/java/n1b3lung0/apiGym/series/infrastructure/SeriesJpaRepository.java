package n1b3lung0.apiGym.series.infrastructure;

import n1b3lung0.apiGym.series.domain.Series;
import n1b3lung0.apiGym.series.domain.SeriesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SeriesJpaRepository extends SeriesRepository, JpaRepository<Series, UUID>, JpaSpecificationExecutor<Series> {
}
