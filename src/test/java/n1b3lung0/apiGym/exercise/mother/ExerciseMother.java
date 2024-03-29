package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.domain.vo.Image;
import n1b3lung0.apiGym.common.domain.vo.Video;
import n1b3lung0.apiGym.common.mother.ImageMother;
import n1b3lung0.apiGym.common.mother.MotherCreator;
import n1b3lung0.apiGym.common.mother.VideoMother;
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
            Image image,
            Video video,
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
                .active(Boolean.TRUE)
                .categories(categories)
                .auditFields(auditFields);
    }
    public static Exercise.ExerciseBuilder random() {
        return create(
                UUID.randomUUID(),
                MotherCreator.random().name().name(),
                MotherCreator.random().book().title(),
                ImageMother.random(),
                VideoMother.random(),
                MotherCreator.random().number().numberBetween(1, 10),
                new HashSet<>(),
                new AuditFields()
        );
    }

    public static List<Exercise> randomList(int num) {
        return IntStream.range(0, num).boxed().map(idx -> random().build()).collect(Collectors.toList());
    }
}
