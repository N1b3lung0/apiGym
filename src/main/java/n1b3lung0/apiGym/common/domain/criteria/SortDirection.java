package n1b3lung0.apiGym.common.domain.criteria;

public enum SortDirection {

    ASC("asc"), DESC("desc");

    private final String type;

    SortDirection(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}
