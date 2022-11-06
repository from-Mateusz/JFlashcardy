package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.DataField;
import cz.mateusz.flashcardy.web.data.TranslatableData;

public class PlayerCredentialsData extends TranslatableData {

    private DataField email;

    private DataField password;

    public DataField getEmail() {
        return email;
    }

    public void setEmail(DataField email) {
        this.email = email;
    }

    public DataField getPassword() {
        return password;
    }

    public void setPassword(DataField password) {
        this.password = password;
    }
}
