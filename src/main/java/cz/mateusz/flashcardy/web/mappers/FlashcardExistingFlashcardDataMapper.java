package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Example;
import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.web.data.ExistingExplanationExampleData;
import cz.mateusz.flashcardy.web.data.ExistingFlashcardData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlashcardExistingFlashcardDataMapper extends ForwardDataModelMapper<Flashcard, ExistingFlashcardData> {

    private LabelLabelDataMapper labelExistingLabelDataMapper;

    private ExampleExistingExplanationExampleDataMapper exampleExistingExplanationExampleDataMapper;

    public FlashcardExistingFlashcardDataMapper(LabelLabelDataMapper labelExistingLabelDataMapper,
                                                ExampleExistingExplanationExampleDataMapper exampleExistingExplanationExampleDataMapper) {
        this.labelExistingLabelDataMapper = labelExistingLabelDataMapper;
        this.exampleExistingExplanationExampleDataMapper = exampleExistingExplanationExampleDataMapper;
    }

    @Override
    public ExistingFlashcardData from(Flashcard source) throws DataModelMapperException {
        ExistingFlashcardData existingFlashcardData = new ExistingFlashcardData();
        existingFlashcardData.setId(source.getId());
        existingFlashcardData.setObjective(source.getObjective().getContent());

        existingFlashcardData.setExplanation(source.getExplanationContent());
        existingFlashcardData.setContext(source.getExplanationContext());
        final List<Example> explanationExamples = source.getExplanation().getExamples();
        ExistingExplanationExampleData examplesData[] = new ExistingExplanationExampleData[source.getExamples().size()];

        int cursor = 0;
        for(Example example : explanationExamples) {
            examplesData[cursor] = exampleExistingExplanationExampleDataMapper.from(example);
            cursor++;
        }
        existingFlashcardData.setExamples(examplesData);

        return existingFlashcardData;
    }
}
