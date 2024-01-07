package n1b3lung0.apiGym.mother.exercise;

import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.mother.common.MotherCreator;

import java.util.UUID;

public final class ExerciseMother {

    private static Exercise.ExerciseBuilder create(
            UUID id,
            String name,
            String description,
            String image,
            String video,
            String restTime,
            AuditFields auditFields
    ) {
        return Exercise.builder()
                .id(id)
                .name(name)
                .description(description)
                .image(image)
                .video(video)
                .restTime(restTime)
                .deleted(Boolean.FALSE)
                .auditFields(auditFields);
    }
    public static Exercise.ExerciseBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().lorem().characters(10, 255),
                MotherCreator.random().lorem().characters(10, 255),
                MotherCreator.random().lorem().characters(),
                MotherCreator.random().lorem().characters(),
                String.valueOf(MotherCreator.random().number().randomDigit()),
                new AuditFields()
        );
    }
}
