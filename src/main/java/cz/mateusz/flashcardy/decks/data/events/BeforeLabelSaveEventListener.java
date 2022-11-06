package cz.mateusz.flashcardy.decks.data.events;

import cz.mateusz.flashcardy.decks.Label;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeforeLabelSaveEventListener extends AbstractMongoEventListener<Label> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforeLabelSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Label> event) {
        Label label = event.getSource();
        if(!label.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(SequenceName.LABELS);
            if(possibleIdSequence.isPresent()) label.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
