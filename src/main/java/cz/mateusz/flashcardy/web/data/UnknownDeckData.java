package cz.mateusz.flashcardy.web.data;

public class UnknownDeckData implements Data {

    private String name;

    private String[] labels;

    private Long ownerId;

    private UnknownFlashcardData[] flashcards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
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
