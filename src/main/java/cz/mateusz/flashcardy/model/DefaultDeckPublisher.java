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
        Optional<Deck> possibleDeckToBePublished = deckRepository.findById(deckId);
        if(!possibleDeckToBePublished.isPresent()) throw new UnknownDeckException(deckId);
        Deck deckToBePublished = possibleDeckToBePublished.get();
        deckToBePublished.setPublished(true);
        return deckToBePublished;
    }
}
