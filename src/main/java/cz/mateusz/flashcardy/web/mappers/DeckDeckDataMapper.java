package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Deck;
import cz.mateusz.flashcardy.web.data.ExistingDeckData;
import org.springframework.stereotype.Component;

@Component
public class DeckDeckDataMapper extends ForwardDataModelMapper<Deck, ExistingDeckData> {
    @Override
    public ExistingDeckData from(Deck source) throws DataModelMapperException {
        ExistingDeckData data = new ExistingDeckData();
        data.setId(source.getId());
        data.setName(source.getName());
        data.setOwnerId(source.getOwner().getId());
        data.setFlashcards(null);
        data.setLabels(null);
        return data;
    }
}
