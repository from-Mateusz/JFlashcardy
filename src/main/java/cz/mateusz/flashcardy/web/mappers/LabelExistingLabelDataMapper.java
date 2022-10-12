package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Label;
import cz.mateusz.flashcardy.web.data.ExistingLabelData;
import org.springframework.stereotype.Component;

@Component
public class LabelExistingLabelDataMapper extends ForwardDataModelMapper<Label, ExistingLabelData> {
    @Override
    public ExistingLabelData from(Label source) throws DataModelMapperException {
        ExistingLabelData labelData = new ExistingLabelData();
        labelData.setLabel(source.getContent());
        labelData.setId(source.getId());
        return labelData;
    }
}
