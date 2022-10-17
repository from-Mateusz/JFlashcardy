package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Deck;
import cz.mateusz.flashcardy.model.Label;
import cz.mateusz.flashcardy.web.data.ExistingDeckData;
import cz.mateusz.flashcardy.web.data.LabelData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeckDeckDataMapper extends ForwardDataModelMapper<Deck, ExistingDeckData> {

    private LabelLabelDataMapper labelLabelDataMapper;

    public DeckDeckDataMapper(LabelLabelDataMapper labelLabelDataMapper) {
        this.labelLabelDataMapper = labelLabelDataMapper;
    }

    @Override
    public ExistingDeckData from(Deck source) throws DataModelMapperException {
        ExistingDeckData data = new ExistingDeckData();
        data.setId(source.getId());
        data.setName(source.getName());
        data.setOwnerId(source.getOwner().getId());
        data.setFlashcards(null);
        List<Label> labels = source.getLabels();
        LabelData[] labelData = new LabelData[labels.size()];
        for(int i = 0; i < labels.size(); i++) labelData[i] = labelLabelDataMapper.from(labels.get(i));
        data.setLabels(labelData);
        return data;
    }
}
