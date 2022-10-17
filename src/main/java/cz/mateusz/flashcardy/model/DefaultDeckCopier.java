package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.DeckCopyRepository;
import cz.mateusz.flashcardy.data.DeckRepository;
import cz.mateusz.flashcardy.data.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDeckCopier implements DeckCopier {

    private DeckRepository deckRepository;

    private PlayerRepository playerRepository;

    private DeckCopyRepository deckCopyRepository;

    public DefaultDeckCopier(DeckRepository deckRepository, PlayerRepository playerRepository, DeckCopyRepository deckCopyRepository) {
        this.deckRepository = deckRepository;
        this.playerRepository = playerRepository;
        this.deckCopyRepository = deckCopyRepository;
    }

    @Override
    public Deck copyFrom(Long foreignDeckId, Long copyOwnerId) throws UnknownDeckException, UnknownPlayerException, PrivateDeckException {
        Optional<Deck> possibleDeckToCopyFrom = deckRepository.findById(foreignDeckId);
        if(!possibleDeckToCopyFrom.isPresent()) throw new UnknownDeckException(foreignDeckId);
        Optional<Player> possibleCopyOwner = playerRepository.findById(copyOwnerId);
        if(!possibleCopyOwner.isPresent()) throw new UnknownPlayerException(copyOwnerId);
        Player copyOwner = possibleCopyOwner.get();
        Deck deckToCopyFrom = possibleDeckToCopyFrom.get();
        if(!deckToCopyFrom.isPublished()) throw new PrivateDeckException(foreignDeckId);
        Deck copiedDeck = deckToCopyFrom.copySelf();
        copiedDeck.setOwner(copyOwner);
        return copiedDeck;
    }

    @Override
    public DeckCopy makeCopyTrackable(Long originalDeckId, Long copiedDeckId) throws UnknownDeckException {
        Optional<Deck> possibleOriginalDeck = deckRepository.findById(originalDeckId);
        if(!possibleOriginalDeck.isPresent()) throw new UnknownDeckException(originalDeckId);
        Optional<Deck> possibleCopiedDeck = deckRepository.findById(copiedDeckId);
        if(!possibleCopiedDeck.isPresent()) throw new UnknownDeckException(copiedDeckId);
        DeckCopy trackableDeckCopy = new DeckCopy(possibleOriginalDeck.get(), possibleCopiedDeck.get());
        deckCopyRepository.save(trackableDeckCopy);
        return trackableDeckCopy;
    }
}
