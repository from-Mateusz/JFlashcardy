package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

import java.util.List;

public class FlashcardExplanationData implements Data {

    private DataField<String> content;

    private DataField<List<FlashcardExplanationExampleData>> examples;

    public DataField<String> getContent() {
        return content;
    }

    public void setContent(DataField<String> content) {
        this.content = content;
    }

    public DataField<List<FlashcardExplanationExampleData>> getExamples() {
        return examples;
    }

    public void setExamples(DataField<List<FlashcardExplanationExampleData>> examples) {
        this.examples = examples;
    }
}
