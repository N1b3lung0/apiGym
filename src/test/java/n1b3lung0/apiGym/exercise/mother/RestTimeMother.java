package n1b3lung0.apiGym.exercise.mother;

import n1b3lung0.apiGym.exercise.domain.RestTime;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RestTimeMother {

    public static RestTime random() {
        List<RestTime> restTimes = Arrays.asList(RestTime.values());
        return restTimes.get(new Random().nextInt(restTimes.size()));
    }
}
