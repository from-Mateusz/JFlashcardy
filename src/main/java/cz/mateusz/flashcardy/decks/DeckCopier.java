package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.model.PrivateDeckException;
import cz.mateusz.flashcardy.model.UnknownDeckException;
import cz.mateusz.flashcardy.model.UnknownPlayerException;

public interface DeckCopier {

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

    /**
     * This method is to help to pin the copy and make it trackable so further building the tree graph
     * consisted of both every copy and its parent will be feasible.
     * @param originalDeckId
     * @param copiedDeckId
     * @return
     * @throws UnknownDeckException
     */
    DeckCopy makeCopyTrackable(Long originalDeckId, Long copiedDeckId) throws UnknownDeckException;
}
