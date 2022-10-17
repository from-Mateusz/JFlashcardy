package cz.mateusz.flashcardy.model;

import java.util.List;

public interface DeckManager {

    List<Deck> fetchEveryPublishedDeck();

    List<Deck> fetchEveryPublishedDeck(Long playerId);

    List<Deck> fetchEveryDeck(Long playerId);

    /**
     * This method is to help a player publish her deck of the flashcards or do the opposite if she will.
     * @param deckId
     * @param resign By this parameter User decides whether her deck is allowed to be published or not.
     *               By default, false means that this deck is free to be published and become usable by other users.
     * @return Deck (the very same) which has been published
     * @throws UnknownDeckException
     */
    Deck publish(Long deckId, boolean resign) throws UnknownDeckException;

    /**
     * This method is to help a player copy other player's deck into her repository provided the deck in question
     * has been already published. In this case copy ought to be understood as a completely new Deck.
     * Copied deck is not publishable.
     * @param foreignDeckId
     * @return DeckCopy which has been copied into player's repository.
     * @throws UnknownDeckException - in case the deck has not been found
     * @throws UnknownPlayerException - in case alleged copy owner (player) has not been found
     * @throws PrivateDeckException - in case the deck is not published
     */
     Deck copyFrom(Long foreignDeckId, Long copyOwnerId) throws UnknownDeckException,
                                                                UnknownPlayerException,
                                                                PrivateDeckException;
}
