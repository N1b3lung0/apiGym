package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.mother.MotherCreator;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.RestTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class ExerciseMother {

    private static Exercise.ExerciseBuilder create(
            UUID id,
            String name,
            String description,
            String image,
            String video,
            RestTime restTime,
            Integer intensity,
            List<Category> categories,
            AuditFields auditFields
    ) {
        return Exercise.builder()
                .id(id)
                .name(name)
                .description(description)
                .image(image)
                .video(video)
                .restTime(restTime)
                .intensity(intensity)
                .categories(categories)
                .deleted(Boolean.FALSE)
                .auditFields(auditFields);
    }
    public static Exercise.ExerciseBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().name().name(),
                MotherCreator.random().book().title(),
                MotherCreator.random().internet().image(),
                MotherCreator.random().internet().url(),
                RestTimeMother.random(),
                MotherCreator.random().number().numberBetween(1, 10),
                new ArrayList<>(),
                new AuditFields()
        );
    }
}
