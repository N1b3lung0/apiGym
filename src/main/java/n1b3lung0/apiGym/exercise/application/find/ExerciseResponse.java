package n1b3lung0.apiGym.exercise.application.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.category.application.find.CategoryResponse;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.common.domain.vo.Image;
import n1b3lung0.apiGym.common.domain.vo.Video;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Exercise response")
public class ExerciseResponse {

    @Schema(description = "Exercise UUID")
    private String id;

    @Schema(description = "Exercise name")
    private String name;

    @Schema(description = "Exercise description")
    private String description;

    @Schema(description = "Exercise image")
    private Image image;

    @Schema(description = "Exercise video")
    private Video video;

    @Schema(description = "Exercise intensity, from 1 to 10")
    private Integer intensity;

    @Schema(description = "Categories which the exercise belongs")
    private Set<CategoryResponse> categories;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the exercise was created")
    private ZonedDateTime createdAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who created the exercise")
    private String createdBy;

    @EqualsAndHashCode.Exclude
    @Schema(description = "When the exercise was updated")
    private ZonedDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Who updated the exercise")
    private String updatedBy;

    public static ExerciseResponse fromExercise(Exercise exercise) {
        return exercise != null ? new ExerciseResponse(
                String.valueOf(exercise.getId()),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getImage(),
                exercise.getVideo(),
                exercise.getIntensity(),
                exercise.getCategories().stream()
                        .filter(category -> !category.isDeleted())
                        .map(CategoryResponse::fromCategory)
                        .collect(Collectors.toSet()),
                exercise.getAuditFields().getCreatedAt(),
                exercise.getAuditFields().getCreatedBy(),
                exercise.getAuditFields().getUpdatedAt(),
                exercise.getAuditFields().getUpdatedBy()
        ) : new ExerciseResponse();
    }
}
