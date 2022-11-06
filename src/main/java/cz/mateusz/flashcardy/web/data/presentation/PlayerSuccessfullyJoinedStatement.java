package cz.mateusz.flashcardy.web.data.presentation;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

public class PlayerSuccessfullyJoinedStatement implements Data {

    private DataField<String> statement;

    public DataField<String> getStatement() {
        return statement;
    }

    public void setStatement(DataField<String> statement) {
        this.statement = statement;
    }
}
