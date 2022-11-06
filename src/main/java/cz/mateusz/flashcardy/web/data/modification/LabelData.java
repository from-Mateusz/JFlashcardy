package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

public class LabelData implements Data {

    private DataField<Long> id;

    private DataField<String> label;

    public DataField<Long> getId() {
        return id;
    }

    public void setId(DataField<Long> id) {
        this.id = id;
    }

    public DataField<String> getLabel() {
        return label;
    }

    public void setLabel(DataField<String> label) {
        this.label = label;
    }
}
