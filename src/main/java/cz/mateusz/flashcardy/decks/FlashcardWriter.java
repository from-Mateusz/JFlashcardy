package cz.mateusz.flashcardy.decks;

public interface FlashcardWriter {
    Flashcard write(Flashcard flashcard);
    boolean erase(Flashcard flashcard);
}
