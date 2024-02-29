package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.mother.MotherCreator;
import n1b3lung0.apiGym.exercise.domain.Exercise;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ExerciseMother {

    private static Exercise.ExerciseBuilder create(
            UUID id,
            String name,
            String description,
            String image,
            String video,
            Integer intensity,
            Set<Category> categories,
            AuditFields auditFields
    ) {
        return Exercise.builder()
                .id(id)
                .name(name)
                .description(description)
                .image(image)
                .video(video)
                .intensity(intensity)
                .deleted(Boolean.FALSE)
                .categories(categories)
                .auditFields(auditFields);
    }
    public static Exercise.ExerciseBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().name().name(),
                MotherCreator.random().book().title(),
                MotherCreator.random().internet().image(),
                MotherCreator.random().internet().url(),
                MotherCreator.random().number().numberBetween(1, 10),
                new HashSet<>(),
                new AuditFields()
        );
    }

    public static List<Exercise> randomList(int num) {
        return IntStream.range(0, num).boxed().map(idx -> random().build()).collect(Collectors.toList());
    }
}
