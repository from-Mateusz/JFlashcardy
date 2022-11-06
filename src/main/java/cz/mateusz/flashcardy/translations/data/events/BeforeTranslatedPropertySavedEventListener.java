package cz.mateusz.flashcardy.translations.data.events;

import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.IdSequenceManager;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.translations.model.TranslatedProperty;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class BeforeTranslatedPropertySavedEventListener extends AbstractMongoEventListener<TranslatedProperty> {

    private IdSequenceManager idSequenceManager;

    public BeforeTranslatedPropertySavedEventListener(IdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<TranslatedProperty> event) {
        final TranslatedProperty translatedProperty = event.getSource();
        if(!translatedProperty.hasId()) {
            final Optional<IdSequence> possibleNextIdSequence = idSequenceManager.getIdSequence(SequenceName.TRANSLATED_PROPERTY);
            if(possibleNextIdSequence.isPresent()) {
                translatedProperty.setId(possibleNextIdSequence.get().getCurrentId());
            }
        }
        super.onBeforeConvert(event);
    }
}
