package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.web.data.UnknownFlashcardData;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnknownFlashcardDataFlashcardMapper extends BackwardDataModelMapper<UnknownFlashcardData, Flashcard> {

    private DeckSeeker deckSeeker;

    public UnknownFlashcardDataFlashcardMapper(DeckSeeker deckSeeker) {
        this.deckSeeker = deckSeeker;
    }

    @Override
    public Flashcard from(UnknownFlashcardData source) throws DataModelMapperException {
        Objective objective = new Objective(source.getObjective());
        Explanation explanation = new Explanation(source.getExplanation(), source.getContext());
        for(String exampleContent : source.getExamples()) explanation.addExample(new Example(exampleContent));
        Flashcard flashcard = new Flashcard(objective, explanation);
        Optional<Deck> possibleDeck = deckSeeker.seekById(source.getDeckId());
        if(possibleDeck.isPresent()) flashcard.setDeck(possibleDeck.get());
        return flashcard;
    }
}
