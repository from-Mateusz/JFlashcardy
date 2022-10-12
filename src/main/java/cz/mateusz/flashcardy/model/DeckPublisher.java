package cz.mateusz.flashcardy.model;

public interface DeckPublisher {
    /**
     * This method is to help a player publish her deck of the flashcards or do the opposite if she will.
     * @param deckId
     * @param resign By this parameter User decides whether her deck is allowed to be published or not.
     *               By default, false means that this deck is free to be published and become usable by other users.
     * @return Deck (the very same) which has been published
     * @throws UnknownDeckException
     */
    Deck publish(Long deckId, boolean resign) throws UnknownDeckException;

}
