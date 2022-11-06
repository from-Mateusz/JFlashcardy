package cz.mateusz.flashcardy.decks.services;

import cz.mateusz.flashcardy.decks.Flashcard;

import java.util.Random;

public class SimpleFlashcardsShuffler {

    public Flashcard[] shuffle(Flashcard[] flashcards, int rounds) {

        if( rounds < 1 ) {
            throw new IllegalArgumentException("Too few rounds for a shuffle to work");
        }

        int cardsNumber = flashcards.length;
        Random random = new Random();
        while(rounds > 0) {
            for(int i = 0; i < cardsNumber; i++) {
                int randomCardIndex = random.nextInt(i + 1 );
                Flashcard swappedCard = flashcards[i];
                flashcards[i] = flashcards[randomCardIndex];
                flashcards[randomCardIndex] = swappedCard;
            }
            rounds--;
        }
        return flashcards;
    }
}
