package duke.labs.rs.data;

public enum UnitOfMeasure {
    S("S"),
    M("M"),
    KG("KG"),
    A("A"),
    C("C"),
    K("K"),
    F("F"),
    MOL("MOL"),
    CD("CD");
    private final String value;

    UnitOfMeasure(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static UnitOfMeasure fromValue(String v) {
        for (UnitOfMeasure c : UnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
