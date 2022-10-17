package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Label;
import cz.mateusz.flashcardy.web.data.LabelData;
import org.springframework.stereotype.Component;

@Component
public class LabelLabelDataMapper extends ForwardDataModelMapper<Label, LabelData> {
    @Override
    public LabelData from(Label source) throws DataModelMapperException {
        LabelData labelData = new LabelData();
        labelData.setLabel(source.getContent());
        labelData.setId(source.getId());
        return labelData;
    }
}
