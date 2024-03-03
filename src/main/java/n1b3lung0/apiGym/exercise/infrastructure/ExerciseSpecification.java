package n1b3lung0.apiGym.exercise.infrastructure;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseSearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class ExerciseSpecification {
    public static Specification<Exercise> byCriteria(ExerciseSearchCriteria criteria) {

        Specification<Exercise> spec = empty();

        String query = criteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Specification<Exercise> byName = byFieldValueLike("name", query);
            Specification<Exercise> byDescription = byFieldValueLike("description", query);
            spec = spec.and(byName.or(byDescription));
        }

        return spec.and(active());
    }

    private static Specification<Exercise> active() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.coalesce().value(root.get("active")), Boolean.TRUE));
    }

    private static Specification<Exercise> byFieldValueLike(String field, String value) {
        return ((root, query, cb) -> cb.like(
                cb.lower(cb.function("unaccent", String.class, root.get(field))),
                "%" + StringUtils.toRootLowerCase(StringUtils.stripAccents(value)) + "%"
        ));
    }

    public static Specification<Exercise> empty() {
        return Specification.where(null);
    }
}
