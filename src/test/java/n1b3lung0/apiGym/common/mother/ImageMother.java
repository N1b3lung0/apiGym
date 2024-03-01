package n1b3lung0.apiGym.common.mother;

import n1b3lung0.apiGym.common.domain.vo.Image;

public final class ImageMother {

    private static Image create(
            String name,
            String description,
            String url
    ) {
        return new Image(name, description, url);
    }

    public static Image random() {
        return create(
                MotherCreator.random().name().name(),
                MotherCreator.random().book().title(),
                MotherCreator.random().internet().url()
        );
    }
}
