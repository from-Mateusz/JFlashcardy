package cz.mateusz.flashcardy.web.data;

public class UnknownFlashcardData implements Data {

    private String objective;

    private String explanation;

    private String context;

    private String[] examples;

    private Long deckId;

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String[] getExamples() {
        return examples;
    }

    public void setExamples(String[] examples) {
        this.examples = examples;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }
}
