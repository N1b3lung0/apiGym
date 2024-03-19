package n1b3lung0.apiGym.workout.infrastructure;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutSearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.Optional;

@UtilityClass
public final class WorkoutSpecification {
    public static Specification<Workout> byCriteria(WorkoutSearchCriteria criteria) {
        Specification<Workout> spec = empty();
        String query = criteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Specification<Workout> byExerciseSeries = byFieldValueLike("name", query); // TODO: Hacerlo
            spec = spec.and(byExerciseSeries);
        }

        ZonedDateTime start = criteria.getStart();
        ZonedDateTime end = criteria.getEnd();
        if(start != null) {
            Specification<Workout> byRange = byRange("endWorkout", start, Optional.ofNullable(criteria.getEnd()).orElse(ZonedDateTime.now()));
            spec = spec.and(byRange);
        } else if(end != null) {
            Specification<Workout> byDateLessThanOrEqualto = byDateLessThanOrEqualto("endWorkout", end);
            spec = spec.and(byDateLessThanOrEqualto);
        }

        return spec;
    }

    private static Specification<Workout> byDateLessThanOrEqualto(String field, ZonedDateTime date) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(field), date));
    }

    private static Specification<Workout> byRange(String field, ZonedDateTime start, ZonedDateTime end) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get(field), start),
                criteriaBuilder.lessThanOrEqualTo(root.get(field), end)));
    }

    private static Specification<Workout> byFieldValueLike(String field, String value) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(criteriaBuilder.function("unaccent", String.class, root.get(field))),
                "%" + StringUtils.toRootLowerCase(StringUtils.stripAccents(value)) + "%"
        ));
    }

    public static Specification<Workout> empty() {
        return Specification.where(null);
    }
}
