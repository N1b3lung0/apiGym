package n1b3lung0.apiGym.exercise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Serie implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final Integer serialNumber;

    private final Integer repetitionsToDo;

    private final Integer repetitionsDone;
}
