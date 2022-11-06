package cz.mateusz.flashcardy.web.data.presentation;

import java.util.List;

public class FlashcardDataPreview {

    private Long id;

    private String objective;

    private List<FlashcardExplanationDataPreview> explanations;

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

    public List<FlashcardExplanationDataPreview> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<FlashcardExplanationDataPreview> explanations) {
        this.explanations = explanations;
    }
}
