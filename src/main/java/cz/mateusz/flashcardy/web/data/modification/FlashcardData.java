package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

import java.util.List;

public class FlashcardData implements Data {

    private DataField<String> objective;

    private DataField<List<FlashcardExplanationData>> explanations;

    public DataField<String> getObjective() {
        return objective;
    }

    public void setObjective(DataField<String> objective) {
        this.objective = objective;
    }

    public DataField<List<FlashcardExplanationData>> getExplanations() {
        return explanations;
    }

    public void setExplanations(DataField<List<FlashcardExplanationData>> explanations) {
        this.explanations = explanations;
    }
}
