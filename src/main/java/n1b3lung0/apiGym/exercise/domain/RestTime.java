package n1b3lung0.apiGym.exercise.domain;

public enum RestTime {
    THIRTY_SECONDS("30"),
    ONE_MINUTE("60"),
    NINETY_SECONDS("90"),
    TWO_MINUTES("120"),
    THREE_MINUTES("180");

    private final String seconds;

    RestTime(String seconds) {
        this.seconds = seconds;
    }

    public String getSeconds() {
        return seconds;
    }
}
