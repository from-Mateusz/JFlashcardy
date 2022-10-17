package cz.mateusz.flashcardy.model;

public class PrivateDeckException extends Exception {

    private Long deckId;

    public PrivateDeckException(Long deckId) {
        this.deckId = deckId;
    }

    public Long getDeckId() {
        return deckId;
    }
}
