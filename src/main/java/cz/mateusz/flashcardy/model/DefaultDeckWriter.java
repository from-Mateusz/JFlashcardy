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
        Deck writtenDeck = deckRepository.save(deck);
        List<DeckLabels> deckLabels = deck.getLabels().stream()
                                                      .map(label -> new DeckLabels(writtenDeck, label))
                                                      .toList();
        deckLabelsRepository.saveAll(deckLabels);
        return writtenDeck;
    }

    @Override
    public boolean erase(Deck deck) {
        deckRepository.delete(deck);
        Optional<Deck> possibleDeck = deckRepository.findById(deck.getId());
        return !(possibleDeck.isPresent());
    }
}
