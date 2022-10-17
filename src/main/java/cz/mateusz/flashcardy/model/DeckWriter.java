package cz.mateusz.flashcardy.model;

public interface DeckWriter {
    Deck write(Deck deck);
    boolean erase(Deck deck);
}
