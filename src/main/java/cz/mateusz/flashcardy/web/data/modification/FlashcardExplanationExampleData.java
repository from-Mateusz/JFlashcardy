package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

public class FlashcardExplanationExampleData implements Data {

    private DataField<String> example;

    public DataField<String> getExample() {
        return example;
    }

    public void setExample(DataField<String> example) {
        this.example = example;
    }
}
