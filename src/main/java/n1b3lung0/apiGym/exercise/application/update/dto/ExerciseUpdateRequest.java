package n1b3lung0.apiGym.exercise.application.update.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseUpdateRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Size(max = 255, message = ExceptionConstants.EXERCISE_NAME_LENGTH_NOT_VALID)
    private String name;

    private String  description;

    private String image;

    private String video;

    private String restTime;
}
