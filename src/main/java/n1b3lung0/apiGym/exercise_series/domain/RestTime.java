package n1b3lung0.apiGym.exercise_series.domain;

import lombok.Getter;

@Getter
public enum RestTime {
    THIRTY_SECONDS("30"),
    ONE_MINUTE("60"),
    NINETY_SECONDS("90"),
    TWO_MINUTES("120"),
    THREE_MINUTES("180"),
    FOUR_MINUTES("240"),
    FIVE_MINUTES("300");

    private final String seconds;

    RestTime(String seconds) {
        this.seconds = seconds;
    }
}
