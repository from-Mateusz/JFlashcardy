package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Deck;
import cz.mateusz.flashcardy.model.DeckLabels;
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
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(CollectionName.DECK_LABELS);
            if(possibleIdSequence.isPresent()) deckLabels.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
