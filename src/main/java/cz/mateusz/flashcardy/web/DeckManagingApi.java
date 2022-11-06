package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.decks.Deck;
import cz.mateusz.flashcardy.decks.DeckCollector;
import cz.mateusz.flashcardy.web.data.modification.DeckData;
import cz.mateusz.flashcardy.web.data.modification.converters.DeckDataConverter;
import cz.mateusz.flashcardy.web.data.presentation.DeckDataPreview;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/decks")
public class DeckManagingApi {

    private DeckCollector deckCollector;

    private DeckDataConverter deckDataConverter;

    public DeckManagingApi(DeckCollector deckCollector) {
        this.deckCollector = deckCollector;
    }

    @PostMapping("/collect")
    @ResponseBody public DeckDataPreview collect(@RequestBody DeckData deckData) {
        Deck deck = deckDataConverter.convert(deckData);
        deckCollector.collect(deck, 0L);
        return null;
    }
}
