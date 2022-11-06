package cz.mateusz.flashcardy.web.data;

import java.util.Objects;

public class DataField<T> {

    private String property;

    private T value;

    public static <T> DataField<T> of(String property, T value) {
        DataField<T> dataField = new DataField<>();
        dataField.setProperty(property);
        dataField.setValue(value);
        return dataField;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataField<?> dataField = (DataField<?>) o;
        return Objects.equals(property, dataField.property) && Objects.equals(value, dataField.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, value);
    }
}
