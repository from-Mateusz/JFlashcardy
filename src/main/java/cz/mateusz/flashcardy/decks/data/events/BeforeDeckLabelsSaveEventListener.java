package cz.mateusz.flashcardy.decks.data.events;

import cz.mateusz.flashcardy.decks.DeckLabels;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeforeDeckLabelsSaveEventListener extends AbstractMongoEventListener<DeckLabels> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforeDeckLabelsSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<DeckLabels> event) {
        DeckLabels deckLabels = event.getSource();
        if(!deckLabels.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(SequenceName.DECK_LABELS);
            if(possibleIdSequence.isPresent()) deckLabels.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
