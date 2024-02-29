package n1b3lung0.apiGym.exercise.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
public final class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    private final String name;
    private final String description;
    private final String url;

    public Image() {
        this.name = null;
        this.description = null;
        this.url = null;
    }
    public Image(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
}
