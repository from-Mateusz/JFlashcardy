package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        return flashcardRepository.save(flashcard);
    }

    @Override
    public boolean erase(Flashcard flashcard) {
        return false;
    }
}
