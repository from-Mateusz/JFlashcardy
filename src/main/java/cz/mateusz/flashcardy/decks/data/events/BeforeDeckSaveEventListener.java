package cz.mateusz.flashcardy.decks.data.events;

import cz.mateusz.flashcardy.decks.Deck;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeforeDeckSaveEventListener extends AbstractMongoEventListener<Deck> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforeDeckSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Deck> event) {
        Deck deck = event.getSource();
        if(!deck.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(SequenceName.DECKS);
            if(possibleIdSequence.isPresent()) deck.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
