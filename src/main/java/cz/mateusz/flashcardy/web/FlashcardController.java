package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.model.FlashcardWriter;
import cz.mateusz.flashcardy.web.data.ExistingFlashcardData;
import cz.mateusz.flashcardy.web.data.UnknownFlashcardData;
import cz.mateusz.flashcardy.web.mappers.DataModelMapperException;
import cz.mateusz.flashcardy.web.mappers.FlashcardExistingFlashcardDataMapper;
import cz.mateusz.flashcardy.web.mappers.UnknownFlashcardDataFlashcardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    @Autowired
    private FlashcardWriter flashcardWriter;

    @Autowired
    private UnknownFlashcardDataFlashcardMapper flashcardDataFlashcardMapper;

    @Autowired
    private FlashcardExistingFlashcardDataMapper flashcardExistingFlashcardDataMapper;

    @PostMapping("/create")
    @ResponseBody
    public ExistingFlashcardData createFlashcard(@RequestBody UnknownFlashcardData flashcardData) throws DataModelMapperException {
        Flashcard flashcard = flashcardDataFlashcardMapper.from(flashcardData);
        flashcardWriter.write(flashcard);
        return flashcardExistingFlashcardDataMapper.from(flashcard);
    }
}
