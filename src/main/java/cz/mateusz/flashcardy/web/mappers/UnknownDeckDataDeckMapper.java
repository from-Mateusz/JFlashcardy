package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.web.data.LabelData;
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
        for(LabelData labelData : source.getLabels()) {
            Label label = new Label(labelData.getLabel());
            label.setId(labelData.getId());
            deck.addLabel(label);
        };
        deck.setPublished(source.isPublished());
        try {
            Player player = playerSeeker.seekPlayerById(source.getOwnerId());
            deck.setOwner(player);
            return deck;
        } catch (UnknownPlayerException ex) {
            throw new DataModelMapperException("Unknown player id");
        }
    }
}
