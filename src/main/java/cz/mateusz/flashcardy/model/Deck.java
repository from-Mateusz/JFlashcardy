package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Cloneable, SelfCopy<Deck>, DomainModel {

    @Id
    private Long id;

    @DocumentReference(lazy = true)
    private Player owner;

    private String name;

    @DocumentReference(lazy = true)
    private List<Flashcard> flashcards;

    @DocumentReference
    private List<Label> labels;

    @Column(name = "Pub")
    private Boolean published;

    public Deck(String name) {
        this.name = name;
        this.flashcards = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    void setId(Long id) {
        this.id = this.id == null ? id : this.id;
    }

    public Long getId() {
        return id;
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
    public Deck copySelf() {
        Deck copy = new Deck(name);
        for(Flashcard flashcard : flashcards) copy.addFlashcard(flashcard.copySelf());
        for(Label label : labels) copy.addLabel(label);
        return copy;
    }
}
