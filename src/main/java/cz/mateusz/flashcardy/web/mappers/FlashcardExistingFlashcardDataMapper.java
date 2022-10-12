package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Example;
import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.model.Label;
import cz.mateusz.flashcardy.web.data.ExistingExplanationExampleData;
import cz.mateusz.flashcardy.web.data.ExistingFlashcardData;
import cz.mateusz.flashcardy.web.data.ExistingLabelData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FlashcardExistingFlashcardDataMapper extends ForwardDataModelMapper<Flashcard, ExistingFlashcardData> {

    private LabelExistingLabelDataMapper labelExistingLabelDataMapper;

    private ExampleExistingExplanationExampleDataMapper exampleExistingExplanationExampleDataMapper;

    public FlashcardExistingFlashcardDataMapper(LabelExistingLabelDataMapper labelExistingLabelDataMapper,
                                                ExampleExistingExplanationExampleDataMapper exampleExistingExplanationExampleDataMapper) {
        this.labelExistingLabelDataMapper = labelExistingLabelDataMapper;
        this.exampleExistingExplanationExampleDataMapper = exampleExistingExplanationExampleDataMapper;
    }

    @Override
    public ExistingFlashcardData from(Flashcard source) throws DataModelMapperException {
        ExistingFlashcardData existingFlashcardData = new ExistingFlashcardData();
        existingFlashcardData.setId(source.getId());
        existingFlashcardData.setObjective(source.getObjective().getContent());
        final List<Label> labels = source.getLabels();
        ExistingLabelData labelData[] = new ExistingLabelData[labels.size()];
        int cursor = 0;
        for(Label objectiveLabel : labels) {
            labelData[cursor] = labelExistingLabelDataMapper.from(objectiveLabel);
            cursor++;
        }
        existingFlashcardData.setLabels(labelData);

        existingFlashcardData.setExplanation(source.getExplanationContent());
        existingFlashcardData.setExplanationContext(source.getExplanationContext());
        final List<Example> explanationExamples = source.getExplanation().getExamples();
        ExistingExplanationExampleData examplesData[] = new ExistingExplanationExampleData[source.getExamples().size()];

        cursor = 0;
        for(Example example : explanationExamples) {
            examplesData[cursor] = exampleExistingExplanationExampleDataMapper.from(example);
            cursor++;
        }
        existingFlashcardData.setExplanationExamples(examplesData);

        return existingFlashcardData;
    }
}
