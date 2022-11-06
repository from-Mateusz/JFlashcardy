package cz.mateusz.flashcardy.decks;

public interface DeckWriter {
    Deck write(Deck deck);
    boolean erase(Deck deck);
}
