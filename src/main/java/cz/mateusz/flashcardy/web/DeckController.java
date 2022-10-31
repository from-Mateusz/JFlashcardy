package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.*;
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
    private DeckPublisher deckPublisher;

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

    @PutMapping("/publish/{deckId}")
    public ExistingDeckData publishDeck(@PathVariable Long deckId, @RequestParam(name = "resign", defaultValue = "false") boolean resign) throws UnknownDeckException, DataModelMapperException {
        Deck publishedDeck = deckPublisher.publish(deckId, resign);
        ExistingDeckData mappedPublishedDeck = deckDeckDataMapper.from(publishedDeck);
        return mappedPublishedDeck;
    }

    @GetMapping("/published/all")
    public ExistingDeckData[] publishDeck() throws DataModelMapperException {
        List<Deck> decks = deckSeeker.seekEveryPublishedDeck();
        ExistingDeckData[] mappedPublishedDecks = new ExistingDeckData[decks.size()];
        for(int i = 0; i < decks.size(); i++) mappedPublishedDecks[i] = deckDeckDataMapper.from(decks.get(i));
        return mappedPublishedDecks;
    }

    @PostMapping("/copy/{deckId}")
    public ExistingDeckData copyPublishedDeck(@PathVariable Long deckId) throws UnknownDeckException,
                                                                                    NonPublishedDeckException,
                                                                                    DataModelMapperException {
        Deck publishedDeck = deckSeeker.seekPublishedDeckById(deckId);
        Deck deckCopy = publishedDeck.copySelf();
        Deck writtenCopiedDeck = deckWriter.write(deckCopy);
        ExistingDeckData mappedCopiedDeck = deckDeckDataMapper.from(deckCopy);
        return mappedCopiedDeck;
    }
}
