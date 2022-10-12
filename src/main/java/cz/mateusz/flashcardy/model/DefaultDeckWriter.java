package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.CollectionName;
import cz.mateusz.flashcardy.data.DeckRepository;
import cz.mateusz.flashcardy.data.IdSequence;
import cz.mateusz.flashcardy.data.NumericIdSequenceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDeckWriter implements DeckWriter {

    private DeckRepository deckRepository;

    private NumericIdSequenceManager idSequenceManager;

    public DefaultDeckWriter(DeckRepository deckRepository, NumericIdSequenceManager idSequenceManager) {
        this.deckRepository = deckRepository;
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public Deck write(Deck deck) {
        if(deck.getId() == null) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getNextIdSequence(CollectionName.DECKS);
            if(possibleIdSequence.isPresent()) deck.setId(possibleIdSequence.get().getNextId());
        }
        Deck writtenDeck = deckRepository.save(deck);
        return writtenDeck;
    }

    @Override
    public boolean erase(Deck deck) {
        deckRepository.delete(deck);
        Optional<Deck> possibleDeck = deckRepository.findById(deck.getId());
        return !(possibleDeck.isPresent());
    }
}
