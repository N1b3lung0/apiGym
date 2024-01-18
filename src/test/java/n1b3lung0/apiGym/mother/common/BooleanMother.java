package n1b3lung0.apiGym.mother.common;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class BooleanMother {

    public static Boolean random() {
        List<Boolean> booleans = Arrays.asList(Boolean.FALSE, Boolean.TRUE);
        return booleans.get(new Random().nextInt(booleans.size()));
    }
}
