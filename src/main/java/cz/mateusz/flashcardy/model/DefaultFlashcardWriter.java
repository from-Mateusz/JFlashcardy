package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultFlashcardWriter implements FlashcardWriter {

    private FlashcardRepository flashcardRepository;

    private LabelRepository labelRepository;

    private NumericIdSequenceManager numericIdSequenceManager;

    public DefaultFlashcardWriter(FlashcardRepository flashcardRepository, LabelRepository labelRepository, NumericIdSequenceManager numericIdSequenceManager) {
        this.flashcardRepository = flashcardRepository;
        this.labelRepository = labelRepository;
        this.numericIdSequenceManager = numericIdSequenceManager;
    }

    @Override
    public Flashcard write(Flashcard flashcard) {
        if(flashcard.getId() == null) {
            Optional<IdSequence> flashcardIdSequence = numericIdSequenceManager.getNextIdSequence(CollectionName.FLASHCARDS);
            if(flashcardIdSequence.isPresent()) flashcard.setId(flashcardIdSequence.get().getNextId());
        }
        for(Label label : flashcard.getLabels()) {
            Optional<IdSequence> labelIdSequence = numericIdSequenceManager.getNextIdSequence(CollectionName.LABELS);
            if(labelIdSequence.isPresent()) label.setId(labelIdSequence.get().getNextId());
        }
        return flashcardRepository.save(flashcard);
    }

    @Override
    public boolean erase(Flashcard flashcard) {
        return false;
    }
}
