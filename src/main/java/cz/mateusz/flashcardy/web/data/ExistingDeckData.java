package cz.mateusz.flashcardy.web.data;

public class ExistingDeckData implements Data {

    private Long id;

    private String name;

    private Long ownerId;

    private LabelData[] labels;

    private ExistingFlashcardData[] flashcards;

    private boolean published;

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

    public LabelData[] getLabels() {
        return labels;
    }

    public void setLabels(LabelData[] labels) {
        this.labels = labels;
    }

    public ExistingFlashcardData[] getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ExistingFlashcardData[] flashcards) {
        this.flashcards = flashcards;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
