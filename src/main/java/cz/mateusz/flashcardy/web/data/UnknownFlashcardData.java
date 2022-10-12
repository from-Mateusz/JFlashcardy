package cz.mateusz.flashcardy.web.data;

public class UnknownFlashcardData implements Data {

    private String objective;

    private String[] Labels;

    private String explanation;

    private String explanationContext;

    private String[] explanationExamples;

    private Long deckId;

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String[] getLabels() {
        return Labels;
    }

    public void setLabels(String[] labels) {
        Labels = labels;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanationContext() {
        return explanationContext;
    }

    public void setExplanationContext(String explanationContext) {
        this.explanationContext = explanationContext;
    }

    public String[] getExplanationExamples() {
        return explanationExamples;
    }

    public void setExplanationExamples(String[] explanationExamples) {
        this.explanationExamples = explanationExamples;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }
}
