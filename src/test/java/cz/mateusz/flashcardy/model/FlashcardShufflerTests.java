package cz.mateusz.flashcardy.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlashcardShufflerTests {

    @Test
    public void shouldNotShuffleWithTooFewRounds() {
        FlashcardsShuffler shuffler = new FlashcardsShuffler();
        Flashcard[] flashcards = generateFirstNFlashcards(10);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> shuffler.shuffle(flashcards, 0));
        assertThat(exception.getMessage(), equalTo("Too few rounds for a shuffle to work"));
    }

    @Test
    public void shouldShuffleCards() {
        FlashcardsShuffler shuffler = new FlashcardsShuffler();
        Flashcard[] flashcards = generateFirstNFlashcards(10);
        displayFlashcards(flashcards);
        Flashcard firstFlashcardBeforeShuffle = flashcards[0];
        Flashcard[] shuffledFlashcards = shuffler.shuffle(flashcards, 1);
        Flashcard firstFlashcardAfterShuffle = shuffledFlashcards[0];
        displayFlashcards(shuffledFlashcards);
        assertThat(flashcards.length, is(10));
        assertThat(firstFlashcardBeforeShuffle, not(equalTo(firstFlashcardAfterShuffle)));
    }

    private Flashcard[] generateFirstNFlashcards(int n) {
        int count = n - 1;
        Flashcard[] flashcards = new Flashcard[n];
        while(count >= 0) {
            Flashcard flashcard = new Flashcard(new Objective("objective #" + (count + 1)),
                                                new Explanation("explanation #" + (count + 1)));
            flashcards[count] = flashcard;
            count--;
        }
        return flashcards;
    }

    private void displayFlashcards(Flashcard[] flashcards) {
        System.out.print("[ ");
        for(int i = 0; i < flashcards.length; i++) {
            Flashcard flashcard = flashcards[i];
            System.out.print("Flashcard: " + flashcard.getObjectiveContent() + " | " + flashcard.getExplanationContent());
            if(i < flashcards.length - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }
}
