package cz.mateusz.flashcardy.web.data;

public class ExistingDeckData implements Data {

    private Long id;

    private String name;

    private Long ownerId;

    private ExistingLabelData[] labels;

    private ExistingFlashcardData[] flashcards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public ExistingLabelData[] getLabels() {
        return labels;
    }

    public void setLabels(ExistingLabelData[] labels) {
        this.labels = labels;
    }

    public ExistingFlashcardData[] getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ExistingFlashcardData[] flashcards) {
        this.flashcards = flashcards;
    }
}
