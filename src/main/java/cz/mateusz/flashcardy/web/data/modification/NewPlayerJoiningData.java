package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

import java.util.Objects;

public class NewPlayerJoiningData implements Data {

    private DataField<String> name;

    private DataField<String> email;

    private DataField<String> password;

    public DataField<String> getName() {
        return name;
    }

    public void setName(DataField<String> name) {
        this.name = name;
    }

    public DataField<String> getEmail() {
        return email;
    }

    public void setEmail(DataField<String> email) {
        this.email = email;
    }

    public DataField<String> getPassword() {
        return password;
    }

    public void setPassword(DataField<String> password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewPlayerJoiningData that = (NewPlayerJoiningData) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, password);
    }
}
