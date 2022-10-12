package cz.mateusz.flashcardy.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class DeckCopy {

    private Long id;

    private Deck source;

    private Deck copy;

    public DeckCopy(Deck source, Deck copy) {
        this.source = source;
        this.copy = copy;
    }

    public Deck getSource() {
        return source;
    }

    public Deck getCopy() {
        return copy;
    }
}
