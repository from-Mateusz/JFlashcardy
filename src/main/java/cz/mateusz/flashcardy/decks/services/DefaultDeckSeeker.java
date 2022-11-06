package cz.mateusz.flashcardy.decks.services;

import cz.mateusz.flashcardy.decks.Deck;
import cz.mateusz.flashcardy.decks.DeckSeeker;
import cz.mateusz.flashcardy.decks.data.DeckRepository;
import cz.mateusz.flashcardy.model.NonPublishedDeckException;
import cz.mateusz.flashcardy.model.UnknownDeckException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultDeckSeeker implements DeckSeeker {

    private DeckRepository deckRepository;

    public DefaultDeckSeeker(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Deck seekDeckById(Long id) throws UnknownDeckException {
        Optional<Deck> possibleDeck = deckRepository.findById(id);
        if(!possibleDeck.isPresent()) throw new UnknownDeckException(id);
        return possibleDeck.get();
    }

    @Override
    public Deck seekPublishedDeckById(Long id) throws UnknownDeckException, NonPublishedDeckException {
        Deck deck = seekDeckById(id);
        if(!deck.isPublished()) throw new NonPublishedDeckException(id);
        return deck;
    }

    @Override
    public List<Deck> seekDecksById(Long[] id) {
        return deckRepository.findAllWithId(id);
    }

    @Override
    public List<Deck> seekEveryPublishedDeck() {
        return deckRepository.findPublished(true);
    }

    @Override
    public List<Deck> seekEveryPublishedDeck(Long ownerId) {
        return deckRepository.findPublishedByOwnerId(ownerId, true);
    }

    @Override
    public List<Deck> seekEveryDeck(Long ownerId) {
        return deckRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Deck> seekEachIncludingPhrase(String phrase) {
        return deckRepository.findEachDeckIncludingPhrase(phrase);
    }
}
