package cz.mateusz.flashcardy.decks.data.events;

import cz.mateusz.flashcardy.decks.Flashcard;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeforeFlashcardSaveEventListener extends AbstractMongoEventListener<Flashcard> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforeFlashcardSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Flashcard> event) {
        Flashcard flashcard = event.getSource();
        if(!flashcard.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(SequenceName.FLASHCARDS);
            if(possibleIdSequence.isPresent()) flashcard.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
