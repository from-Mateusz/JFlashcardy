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
        Explanation explanation = new Explanation(source.getExplanation(), source.getExplanationContext());
        for(String example : source.getExplanationExamples()) explanation.addExample(new Example(example));
        Flashcard flashcard = new Flashcard(objective, explanation);
        Optional<Deck> possibleDeck = deckSeeker.seekById(source.getDeckId());
        if(possibleDeck.isPresent()) flashcard.setDeck(possibleDeck.get());
        for(String label : source.getLabels()) flashcard.addLabel(new Label(label));
        return flashcard;
    }
}
