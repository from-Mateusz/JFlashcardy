package cz.mateusz.flashcardy.model;

public interface FlashcardWriter {
    Flashcard write(Flashcard flashcard);
    boolean erase(Flashcard flashcard);
}
