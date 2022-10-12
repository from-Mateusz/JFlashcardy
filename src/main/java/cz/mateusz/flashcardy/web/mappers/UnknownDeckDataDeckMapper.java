package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.web.data.UnknownDeckData;
import cz.mateusz.flashcardy.web.data.UnknownFlashcardData;
import org.springframework.stereotype.Component;

@Component
public class UnknownDeckDataDeckMapper extends BackwardDataModelMapper<UnknownDeckData, Deck> {

    private UnknownFlashcardDataFlashcardMapper flashcardDataMapper;

    private PlayerSeeker playerSeeker;

    public UnknownDeckDataDeckMapper(UnknownFlashcardDataFlashcardMapper flashcardDataMapper, PlayerSeeker playerSeeker) {
        this.flashcardDataMapper = flashcardDataMapper;
        this.playerSeeker = playerSeeker;
    }

    @Override
    public Deck from(UnknownDeckData source) throws DataModelMapperException {
        Deck deck = new Deck(source.getName());
        for(String label : source.getLabels()) deck.addLabel(new Label(label));
        for(UnknownFlashcardData flashcard : source.getFlashcards()) deck.addFlashcard(flashcardDataMapper.from(flashcard));
        try {
            Player player = playerSeeker.seekPlayerById(source.getOwnerId());
            deck.setOwner(player);
            return deck;
        } catch (UnknownPlayerException ex) {
            throw new DataModelMapperException("Unknown player id");
        }
    }
}
