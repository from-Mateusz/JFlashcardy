package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import java.util.*;

@Document
public class Flashcard extends DomainEntity implements Cloneable, SelfCopy<Flashcard> {

    private Objective objective;

    private Explanation explanation;

    @DocumentReference(lazy = true)
    private Deck deck;

    public Flashcard() {}

    public Flashcard(Objective objective, Explanation explanation) {
        this.objective = objective;
        this.explanation = explanation;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Explanation getExplanation() {
        return explanation;
    }

    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    public void clearObjective() {
        objective = null;
    }

    public void clearExplanation() {
        explanation.clearExamples();
        explanation = null;
    }

    public String getObjectiveContent() {
        return objective.getContent();
    }

    public String getExplanationContent() {
        return explanation.getContent();
    }

    public String getExplanationContext() {
        return explanation.getContext();
    }

    public List<Example> getExamples() {
        return explanation.getExamples();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        deck.addFlashcard(this);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Flashcard clone = (Flashcard) super.clone();
        clone.clearExplanation();
        clone.clearObjective();
        clone.setObjective((Objective) objective.clone());
        clone.setExplanation((Explanation) explanation.clone());
        return clone;
    }

    @Override
    public Flashcard copySelf() {
        Flashcard copy = new Flashcard(objective.copySelf(), explanation.copySelf());
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(objective, flashcard.objective) && Objects.equals(explanation, flashcard.explanation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objective, explanation);
    }
}
