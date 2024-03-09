package n1b3lung0.apiGym.exercise.application.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.common.domain.vo.Image;
import n1b3lung0.apiGym.common.domain.vo.Video;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Exercise create request")
public class ExerciseCreateRequest {

    @NotBlank
    @Size(min = 4, max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    @Schema(description = "Exercise name")
    private String name;

    @Schema(description = "Exercise description")
    private String description;

    @Schema(description = "Exercise image")
    private Image image;

    @Schema(description = "Exercise video")
    private Video video;

    @Schema(description = "Exercise intensity, from 1 to 10")
    @Min(value = 1, message = ExceptionConstants.EXERCISE_INTENSITY_MIN)
    @Max(value = 10, message = ExceptionConstants.EXERCISE_INTENSITY_MAX)
    private Integer intensity;

    @Schema(description = "Categories which the exercise belongs")
    private Set<String> categoryIds;

    public Exercise toExercise(
            String name,
            String description,
            Image image,
            Video video,
            Integer intensity
    ) {
        return Exercise.create(
                name,
                description,
                image,
                video,
                intensity
        );
    }

    public static ExerciseCreateRequest fromExercise(Exercise exercise) {
        return new ExerciseCreateRequest(
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getIntensity(),
                exercise.getCategories().stream()
                        .map(category -> category.getId().toString())
                        .collect(Collectors.toSet())
        );
    }
}
