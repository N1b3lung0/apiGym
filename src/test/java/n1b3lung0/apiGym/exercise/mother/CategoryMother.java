package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.mother.MotherCreator;
import n1b3lung0.apiGym.exercise.domain.Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CategoryMother {

    private static Category.CategoryBuilder create(
            UUID id,
            String name,
            List<Exercise> exercises,
            AuditFields auditFields
    ) {
        return Category.builder()
                .id(id)
                .name(name)
                .exercises(exercises)
                .auditFields(auditFields);
    }

    public static Category.CategoryBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().name().name(),
                new ArrayList<>(),
                new AuditFields()
        );
    }
}
