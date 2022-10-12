package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.DeckRepository;
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
    public Optional<Deck> seekById(Long id) {
        return deckRepository.findById(id);
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
}
