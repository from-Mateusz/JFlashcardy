package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document("decks_labels")
@CompoundIndex(unique = true, def = "{ 'deck': 1, 'label': 1 }")
public class DeckLabels extends DomainEntity {

    @DocumentReference
    @Field(name = "deck")
    private Deck deck;

    @DocumentReference
    @Field(name = "label")
    private Label label;

    DeckLabels() {}

    public DeckLabels(Deck deck, Label label) {
        this.deck = deck;
        this.label = label;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckLabels that = (DeckLabels) o;
        return Objects.equals(deck, that.deck) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, label);
    }
}
