package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.model.Label;
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
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(CollectionName.LABELS);
            if(possibleIdSequence.isPresent()) label.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
