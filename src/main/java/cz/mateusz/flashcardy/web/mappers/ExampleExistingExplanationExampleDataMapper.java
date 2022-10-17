package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Example;
import cz.mateusz.flashcardy.web.data.ExistingExplanationExampleData;
import org.springframework.stereotype.Component;

@Component
public class ExampleExistingExplanationExampleDataMapper extends ForwardDataModelMapper<Example, ExistingExplanationExampleData> {
    @Override
    public ExistingExplanationExampleData from(Example source) throws DataModelMapperException {
        ExistingExplanationExampleData exampleData = new ExistingExplanationExampleData();
        exampleData.setExample(source.getContent());
        return exampleData;
    }
}
