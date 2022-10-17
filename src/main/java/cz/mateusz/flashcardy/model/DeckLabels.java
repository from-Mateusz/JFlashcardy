package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@CompoundIndex(unique = true, def = "{ 'deck': 1, 'stock': 1 }")
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
}
