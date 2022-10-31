package cz.mateusz.flashcardy.exception;

public class ErroneousProperty {

    private String name;

    private Object value;

    public ErroneousProperty() {}

    public ErroneousProperty(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static <T extends Number> ErroneousProperty of(String name, T value) {
        return new ErroneousProperty(name, value);
    }

    public static ErroneousProperty of(String name, String value) {
        return new ErroneousProperty(name, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
