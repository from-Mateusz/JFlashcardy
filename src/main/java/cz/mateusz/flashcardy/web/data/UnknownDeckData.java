package cz.mateusz.flashcardy.web.data;

public class UnknownDeckData implements Data {

    private String name;

    private LabelData[] labels;

    private Long ownerId;

    private UnknownFlashcardData[] flashcards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LabelData[] getLabels() {
        return labels;
    }

    public void setLabels(LabelData[] labels) {
        this.labels = labels;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public UnknownFlashcardData[] getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(UnknownFlashcardData[] flashcards) {
        this.flashcards = flashcards;
    }
}
