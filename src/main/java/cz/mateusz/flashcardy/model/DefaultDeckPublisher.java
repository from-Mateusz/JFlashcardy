package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDeckPublisher implements DeckPublisher {

    private DeckRepository deckRepository;

    public DefaultDeckPublisher(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Deck publish(Long deckId, boolean resign) throws UnknownDeckException {
        Optional<Deck> possibleDeck = deckRepository.findById(deckId);
        if(!possibleDeck.isPresent()) throw new UnknownDeckException(deckId);
        Deck publishableDeck = possibleDeck.get();
        publishableDeck.setPublished(resign ? false : true);
        deckRepository.save(publishableDeck);
        return publishableDeck;
    }
}
