package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.model.DomainEntity;
import cz.mateusz.flashcardy.helpers.SelfCopy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;

@Document
public class Flashcard extends DomainEntity {

    private String objective;

    @DocumentReference(lazy = true)
    private List<Explanation> explanations = new ArrayList<>();

    @DocumentReference(lazy = true)
    private Deck deck;

    public Flashcard() {}

    public Flashcard(String objective, Explanation explanation) {
        this.objective = objective;
        this.explanations.add(explanation);
    }

    public Flashcard(String objective, List<Explanation> explanations) {
        this.objective = objective;
        this.explanations.addAll(explanations);
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public List<Explanation> getExplanations() {
        return explanations;
    }

    public void addExplanations(List<Explanation> explanations) {
        this.explanations.addAll(explanations);
    }

    public void clearObjective() {
        objective = null;
    }

    public void clearExplanation() {
        ;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        deck.addFlashcard(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(objective, flashcard.objective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objective);
    }
}
