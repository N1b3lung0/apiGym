package n1b3lung0.apiGym.category.mother;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.mother.MotherCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CategoryMother {

    private static Category.CategoryBuilder create(
            UUID id,
            String name,
            AuditFields auditFields
    ) {
        return Category.builder()
                .id(id)
                .name(name)
                .auditFields(auditFields);
    }

    public static Category.CategoryBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().name().name(),
                new AuditFields()
        );
    }

    public static List<Category> randomList(int num) {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            categories.add(random().build());
        }
        return categories;
    }
}
