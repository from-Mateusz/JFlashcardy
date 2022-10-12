package cz.mateusz.flashcardy.model;

import java.util.List;
import java.util.Optional;

public interface DeckSeeker {

    Optional<Deck> seekById(Long id);
    List<Deck> seekEveryPublishedDeck();

    List<Deck> seekEveryPublishedDeck(Long ownerId);

    List<Deck> seekEveryDeck(Long ownerId);
}
