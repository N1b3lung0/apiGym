package n1b3lung0.apiGym.category.infrastructure;

import lombok.experimental.UtilityClass;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategorySearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class CategorySpecification {
    public static Specification<Category> byCriteria(CategorySearchCriteria criteria) {
        Specification<Category> spec = empty();
        String query = criteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Specification<Category> byName = byFieldValueLike("name", query);
            spec = spec.and(byName);
        }
        return spec.and(notDeleted());
    }

    private static Specification<Category> notDeleted() {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.coalesce().value(root.get("deleted")), Boolean.FALSE )));
    }

    private static Specification<Category> byFieldValueLike(String field, String value) {
        return (((root, query, cb) -> cb.like(
                cb.lower(cb.function("unaccent", String.class, root.get(field))),
                "%" + StringUtils.toRootLowerCase(StringUtils.stripAccents(value)) + "%"
        )));
    }

    public static Specification<Category> empty() {
        return Specification.where(null);
    }
}
