package cz.mateusz.flashcardy.web.validators;

public class DataValidationError {

    private String property;

    private Object value;

    public DataValidationError() {}

    public DataValidationError(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
