package cz.mateusz.flashcardy.web.data;

public class ExistingFlashcardData implements Data {

    private Long id;

    private String objective;

    private String explanation;

    private String context;

    private ExistingExplanationExampleData[] examples;

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

    public ExistingExplanationExampleData[] getExamples() {
        return examples;
    }

    public void setExamples(ExistingExplanationExampleData[] examples) {
        this.examples = examples;
    }
}
