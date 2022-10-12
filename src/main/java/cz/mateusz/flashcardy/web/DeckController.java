package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.Deck;
import cz.mateusz.flashcardy.model.DeckSeeker;
import cz.mateusz.flashcardy.model.DeckWriter;
import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.web.data.ExistingDeckData;
import cz.mateusz.flashcardy.web.data.ExistingFlashcardData;
import cz.mateusz.flashcardy.web.data.UnknownDeckData;
import cz.mateusz.flashcardy.web.data.UnknownFlashcardData;
import cz.mateusz.flashcardy.web.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckWriter deckWriter;

    @Autowired
    private DeckSeeker deckSeeker;

    @Autowired
    private UnknownDeckDataDeckMapper unknownDeckDataDeckMapper;

    @Autowired
    private DeckDeckDataMapper deckDeckDataMapper;

    @Autowired
    private FlashcardExistingFlashcardDataMapper flashcardDataMapper;

    @PostMapping("/create")
    @ResponseBody
    public ExistingDeckData createDeck(@RequestBody UnknownDeckData unknownDeckData) throws DataModelMapperException {
        Deck mappedDeck = unknownDeckDataDeckMapper.from(unknownDeckData);
        Deck writtenDeck = deckWriter.write(mappedDeck);
        ExistingDeckData mappedWrittenDeck = deckDeckDataMapper.from(writtenDeck);
        return mappedWrittenDeck;
    }
}
