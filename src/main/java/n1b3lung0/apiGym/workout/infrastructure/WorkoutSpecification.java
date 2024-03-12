package n1b3lung0.apiGym.workout.infrastructure;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutSearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class WorkoutSpecification {
    public static Specification<Workout> byCriteria(WorkoutSearchCriteria criteria) {
        Specification<Workout> spec = empty();
        String query = criteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Specification<Workout> byExerciseSeries = byFieldValueLike("exerciseSeries", query);
            spec = spec.and(byExerciseSeries);
        }
        return spec;
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
