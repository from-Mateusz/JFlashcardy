package cz.mateusz.flashcardy.model;

import java.util.List;
import java.util.Optional;

public interface DeckSeeker {

    Deck seekDeckById(Long id) throws UnknownDeckException;

    Deck seekPublishedDeckById(Long id) throws UnknownDeckException, NonPublishedDeckException;

    List<Deck> seekDecksById(Long[] id);

    List<Deck> seekEveryPublishedDeck();

    List<Deck> seekEveryPublishedDeck(Long ownerId);

    List<Deck> seekEveryDeck(Long ownerId);

    List<Deck> seekEachIncludingPhrase(String phrase);
}
