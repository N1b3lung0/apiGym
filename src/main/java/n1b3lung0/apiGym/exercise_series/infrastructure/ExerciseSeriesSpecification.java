package n1b3lung0.apiGym.exercise_series.infrastructure;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeries;
import n1b3lung0.apiGym.exercise_series.domain.ExerciseSeriesSearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class ExerciseSeriesSpecification {

    public static Specification<ExerciseSeries> byCriteria(ExerciseSeriesSearchCriteria criteria) {
        Specification<ExerciseSeries> spec = empty();
        String query = criteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Specification<ExerciseSeries> byExercise = byFieldValueLike("exercise", query);
            spec = spec.and(byExercise);
        }
        return spec;
    }

    private static Specification<ExerciseSeries> byFieldValueLike(String field, String value) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(criteriaBuilder.function("unaccent", String.class, root.get(field))),
                "%" + StringUtils.toRootLowerCase(StringUtils.stripAccents(value)) + "%"
        )));
    }

    public static Specification<ExerciseSeries> empty() {
        return Specification.where(null);
    }
}
