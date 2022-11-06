package cz.mateusz.flashcardy.decks;

public interface DeckCollector {

    Deck collect(Deck deck, Long ownerId);

    Deck copyAndCollect(Long publishedDeckId, Long ownerId);

    Deck getCollected(Long ownerId);

    Deck getCollectedPublished(Long ownerId);

}
