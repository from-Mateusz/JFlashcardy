package cz.mateusz.flashcardy.web.data.modification.converters;

import cz.mateusz.flashcardy.decks.*;
import cz.mateusz.flashcardy.web.data.modification.DeckData;
import cz.mateusz.flashcardy.web.data.modification.FlashcardData;
import cz.mateusz.flashcardy.web.data.modification.LabelData;

import java.util.List;

public class DeckDataConverter implements DataConverter<DeckData, Deck> {
    @Override
    public Deck convert(DeckData data) {
        Deck deck = new Deck(data.getName().getValue());
        List<FlashcardData> flashcards = data.getFlashcards().getValue();
        List<LabelData> labels = data.getLabels().getValue();

        flashcards.forEach(flashcard -> {
            List<Explanation> explanations = flashcard.getExplanations().getValue()
                                            .stream().map(flashcardExplanationData -> {
                                                Explanation explanation = new Explanation(flashcardExplanationData.getContent().getValue());
                                                List<Example> examples = flashcardExplanationData.getExamples().getValue()
                                                                                .stream()
                                                                                .map( flashcardExplanationExampleData -> new Example(flashcardExplanationExampleData.getExample().getValue()))
                                                                                .toList();
                                                explanation.addExamples(examples);
                                                return explanation;
                                            })
                                            .toList();
            Flashcard convertedFlashcard = new Flashcard(flashcard.getObjective().getValue(), explanations);
            deck.addFlashcard(convertedFlashcard);
        });

        labels.forEach(label -> {
            Label convertedLabel = new Label(label.getLabel().getValue());
            convertedLabel.setId(label.getId().getValue());
            deck.addLabel(convertedLabel);
        });

        return deck;
    }
}
