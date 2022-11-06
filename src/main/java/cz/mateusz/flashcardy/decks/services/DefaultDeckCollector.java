package cz.mateusz.flashcardy.decks.services;

import cz.mateusz.flashcardy.decks.Deck;
import cz.mateusz.flashcardy.decks.DeckCollector;
import cz.mateusz.flashcardy.decks.Label;
import cz.mateusz.flashcardy.decks.data.DeckRepository;
import cz.mateusz.flashcardy.decks.data.LabelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultDeckCollector implements DeckCollector {

    private DeckRepository deckRepository;

    private LabelRepository labelRepository;

    @Override
    @Transactional
    public Deck collect(Deck deck, Long ownerId) {
        Set<Label> collectedLabels = collectLabelsIfNecessary(deck.getLabels());
        deck.addLabels(collectedLabels);
        Deck collectedDeck = deckRepository.save(deck);
        return collectedDeck;
    }

    @Override
    public Deck copyAndCollect(Long publishedDeckId, Long ownerId) {
        return null;
    }

    @Override
    public Deck getCollected(Long ownerId) {
        return null;
    }

    @Override
    public Deck getCollectedPublished(Long ownerId) {
        return null;
    }

    private Set<Label> collectLabelsIfNecessary(List<Label> labels) {
        if(labels.size() < 1) return Collections.emptySet();
        Set<Label> collectedLabels = labels.stream().filter(label -> label.hasId())
                                                    .collect(Collectors.toSet());
        if(labels.size() == 0) return collectedLabels;
        labelRepository.saveAll(collectedLabels);
        return collectedLabels;
    }
}
