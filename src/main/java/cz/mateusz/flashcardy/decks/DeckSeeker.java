package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.model.NonPublishedDeckException;
import cz.mateusz.flashcardy.model.UnknownDeckException;

import java.util.List;

public interface DeckSeeker {

    Deck seekDeckById(Long id) throws UnknownDeckException;

    Deck seekPublishedDeckById(Long id) throws UnknownDeckException, NonPublishedDeckException;

    List<Deck> seekDecksById(Long[] id);

    List<Deck> seekEveryPublishedDeck();

    List<Deck> seekEveryPublishedDeck(Long ownerId);

    List<Deck> seekEveryDeck(Long ownerId);

    List<Deck> seekEachIncludingPhrase(String phrase);
}
