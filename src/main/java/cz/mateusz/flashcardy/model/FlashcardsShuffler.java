package cz.mateusz.flashcardy.model;

import java.util.Random;

public class FlashcardsShuffler {

    /**
     * This method is to perform a deep copy of a given array of flashcards before any other operations on it.
     * Outcome is the utterly new array of a shuffled flashcards. This method allows user to set shuffling rounds,
     * namely it is to enhance "entropy" of the order of the flashcards. Also bear in mind, that it is required to
     * provided at least one round of the shuffling.
     * The time complexity (big O) for this method might be expressed as:
     * c(n) = O(n)
     * c - number of rounds.
     * @param flashcards
     * @param rounds
     * @return
     */
    public Flashcard[] shuffle(Flashcard[] flashcards, int rounds) {

        if( rounds < 1 ) {
            throw new IllegalArgumentException("Too few rounds for a shuffle to work");
        }

        Flashcard[] shuffledCards = tryDeepCopy(flashcards);
        int cardsNumber = shuffledCards.length;
        Random random = new Random();
        while(rounds > 0) {
            for(int i = 0; i < cardsNumber; i++) {
                int randomCardIndex = random.nextInt(i + 1 );
                Flashcard swappedCard = shuffledCards[i];
                shuffledCards[i] = shuffledCards[randomCardIndex];
                shuffledCards[randomCardIndex] = swappedCard;
            }
            rounds--;
        }

        return shuffledCards;
    }

    private Flashcard[] tryDeepCopy(Flashcard[] flashcards) {
        Flashcard[] copy = new Flashcard[flashcards.length];
        try {
            for(int i = 0; i < copy.length; i++) {
                copy[i] = (Flashcard) (flashcards[i].clone());
            }
            // Assumed filled copy
            return copy;
        } catch (Exception ex) {
            // Assumed empty copy
            return copy;
        }
    }
}
