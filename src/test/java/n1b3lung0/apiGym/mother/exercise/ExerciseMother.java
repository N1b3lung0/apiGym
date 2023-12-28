package n1b3lung0.apiGym.mother.exercise;

import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.mother.common.MotherCreator;

import java.util.UUID;

public final class ExerciseMother {

    private static Exercise.ExerciseBuilder create(
            UUID id,
            String name,
            String description,
            String image,
            String video,
            int restTime,
            boolean deleted
    ) {
        return Exercise.builder()
                .id(id)
                .name(name)
                .description(description)
                .image(image)
                .video(video)
                .restTime(restTime)
                .deleted(deleted);
    }
    public static Exercise.ExerciseBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().lorem().characters(10, 255),
                MotherCreator.random().lorem().characters(10, 255),
                MotherCreator.random().lorem().characters(),
                MotherCreator.random().lorem().characters(),
                MotherCreator.random().number().randomDigit(),
                Boolean.FALSE
        );
    }
}
