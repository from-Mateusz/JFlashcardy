package cz.mateusz.flashcardy.web.data.modification;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.data.DataField;

import java.util.List;

public class DeckData implements Data {

    private DataField<String> name;

    private DataField<Long> ownerId;

    private DataField<Boolean> published;

    private DataField<List<FlashcardData>> flashcards;

    private DataField<List<LabelData>> labels;

    public DataField<String> getName() {
        return name;
    }

    public void setName(DataField<String> name) {
        this.name = name;
    }

    public DataField<Long> getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(DataField<Long> ownerId) {
        this.ownerId = ownerId;
    }

    public DataField<Boolean> getPublished() {
        return published;
    }

    public void setPublished(DataField<Boolean> published) {
        this.published = published;
    }

    public DataField<List<FlashcardData>> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(DataField<List<FlashcardData>> flashcards) {
        this.flashcards = flashcards;
    }

    public DataField<List<LabelData>> getLabels() {
        return labels;
    }

    public void setLabels(DataField<List<LabelData>> labels) {
        this.labels = labels;
    }
}
