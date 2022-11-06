package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.ModelEntity;
import cz.mateusz.flashcardy.model.DomainEntity;
import cz.mateusz.flashcardy.helpers.SelfCopy;
import cz.mateusz.flashcardy.players.model.Player;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document("decks")
public class Deck extends ModelEntity {

    @DocumentReference(lazy = true)
    private Player owner;

    @Indexed
    private String name;

    @DocumentReference(lazy = true)
    private List<Flashcard> flashcards;

    @DocumentReference
    private List<Label> labels;

    @Field(value = "published", write = Field.Write.ALWAYS)
    private Boolean published;

    public Deck(String name) {
        this.name = name;
        this.flashcards = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Flashcard> getFlashcards() {
        return Collections.unmodifiableList(flashcards);
    }

    public Flashcard[] getFlashcardsArray() {
        Flashcard[] cardsArray = new Flashcard[flashcards.size()];
        return flashcards.toArray(cardsArray);
    }

    public boolean addFlashcard(Flashcard flashcard) {
        if(flashcard.getDeck() != this) flashcard.setDeck(this);
        return flashcards.add(flashcard);
    }

    public List<Label> getLabels() {
        return Collections.unmodifiableList(labels);
    }

    public void clearLabels() {
        labels.clear();
    }

    public boolean addLabels(Set<Label> labels) {
        for(Label label: labels) addLabel(label);
        return this.labels.size() == labels.size();
    }

    public boolean addLabel(Label label) {
        return labels.add(label);
    }

    public boolean isPublished() {
        return published == null ? false : published;
    }

    public void setPublished(boolean decision) {
        this.published = decision;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deck deck = (Deck) o;
        return Objects.equals(owner, deck.owner) && Objects.equals(name, deck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner, name);
    }
}
