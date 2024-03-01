package n1b3lung0.apiGym.common.mother;

import n1b3lung0.apiGym.common.domain.vo.Video;

public final class VideoMother {

    private static Video create(
            String name,
            String description,
            String url
    ) {
        return new Video(name, description, url);
    }

    public static Video random() {
        return create(
                MotherCreator.random().name().name(),
                MotherCreator.random().book().title(),
                MotherCreator.random().internet().url()
        );
    }
}
