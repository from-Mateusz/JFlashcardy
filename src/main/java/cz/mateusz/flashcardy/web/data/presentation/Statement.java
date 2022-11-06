package cz.mateusz.flashcardy.web.data.presentation;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

public class Statement implements Data {

    private boolean success;

    private DataField<String> statement;

    public Statement() {}

    public Statement(DataField<String> statement, boolean success) {
        this.statement = statement;
        this.success = success;
    }

    public DataField<String> getStatement() {
        return statement;
    }

    public void setStatement(DataField<String> statement) {
        this.statement = statement;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
