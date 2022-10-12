package cz.mateusz.flashcardy.web.data;

public class ExistingFlashcardData implements Data {

    private Long id;

    private String objective;

    private ExistingLabelData[] labels;

    private String explanation;

    private String explanationContext;

    private ExistingExplanationExampleData[] explanationExamples;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public ExistingLabelData[] getLabels() {
        return labels;
    }

    public void setLabels(ExistingLabelData[] labels) {
        this.labels = labels;
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

    public ExistingExplanationExampleData[] getExplanationExamples() {
        return explanationExamples;
    }

    public void setExplanationExamples(ExistingExplanationExampleData[] explanationExamples) {
        this.explanationExamples = explanationExamples;
    }
}
