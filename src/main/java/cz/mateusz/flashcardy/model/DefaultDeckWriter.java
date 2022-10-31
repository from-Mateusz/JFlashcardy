package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DefaultDeckWriter implements DeckWriter {

    private DeckRepository deckRepository;

    private LabelRepository labelRepository;

    private DeckLabelsRepository deckLabelsRepository;

    private NumericIdSequenceManager idSequenceManager;

    public DefaultDeckWriter(DeckRepository deckRepository,
                             LabelRepository labelRepository,
                             DeckLabelsRepository deckLabelsRepository,
                             NumericIdSequenceManager idSequenceManager) {
        this.deckRepository = deckRepository;
        this.labelRepository = labelRepository;
        this.deckLabelsRepository = deckLabelsRepository;
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public Deck write(Deck deck) {
        deck.getLabels().forEach(label -> {
            if(!label.hasId()) labelRepository.save(label);
        });
        List<DeckLabels> deckLabels = deck.getLabels().stream()
                                            .map(label -> new DeckLabels(deck, label))
                                            .toList();
        if(!deck.hasId()) {
            Deck writtenDeck = deckRepository.save(deck);
            deckLabelsRepository.saveAll(deckLabels);
            return writtenDeck;
        }
        else {
            List<DeckLabels> existingDeckLabels = deckLabelsRepository.findAllByDeckId(deck.getId());
            existingDeckLabels.removeAll(deckLabels);
            deckLabelsRepository.saveAll(deckLabels);
            Deck writtenDeck = deckRepository.save(deck);
            return writtenDeck;
        }
    }

    @Override
    public boolean erase(Deck deck) {
        deckRepository.delete(deck);
        Optional<Deck> possibleDeck = deckRepository.findById(deck.getId());
        return !(possibleDeck.isPresent());
    }
}
