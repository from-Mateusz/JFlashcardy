package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.Deck;
import cz.mateusz.flashcardy.model.DeckSeeker;
import cz.mateusz.flashcardy.model.DeckWriter;
import cz.mateusz.flashcardy.web.data.ExistingDeckData;
import cz.mateusz.flashcardy.web.data.UnknownDeckData;
import cz.mateusz.flashcardy.web.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * I think, there is no need to immediately return mapped deck objects.
     * In case, there are no decks, empty id array is return. Otherwise,
     * array of ids is returned and frontend is put in charge of fetching
     * as many mapped deck objects as it wants by the given ids.
     * @param phrase
     * @return
     */
    @GetMapping("/find/by")
    public ExistingDeckData[] findDecks(@RequestParam("phrase") String phrase) throws DataModelMapperException {
        List<Deck> decks = deckSeeker.seekEachIncludingPhrase(phrase);
        ExistingDeckData[] mappedDecks = new ExistingDeckData[decks.size()];
        for(int i = 0; i < decks.size(); i++) mappedDecks[i] = deckDeckDataMapper.from(decks.get(i));
        return mappedDecks;
    }
}
