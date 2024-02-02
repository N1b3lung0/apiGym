package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.mother.MotherCreator;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.RestTime;

import java.util.UUID;

public final class ExerciseMother {

    private static Exercise.ExerciseBuilder create(
            UUID id,
            String name,
            String description,
            String image,
            String video,
            RestTime restTime,
            Integer intensity,
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
                new AuditFields()
        );
    }
}
