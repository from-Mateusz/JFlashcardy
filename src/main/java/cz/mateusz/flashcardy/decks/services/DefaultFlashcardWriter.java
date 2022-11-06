package cz.mateusz.flashcardy.decks.services;

import cz.mateusz.flashcardy.decks.Flashcard;
import cz.mateusz.flashcardy.decks.FlashcardWriter;
import cz.mateusz.flashcardy.decks.data.FlashcardRepository;
import cz.mateusz.flashcardy.decks.data.LabelRepository;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.stereotype.Service;

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
