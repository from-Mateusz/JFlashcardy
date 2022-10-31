package cz.mateusz.flashcardy.model;

public class NonPublishedDeckException extends DomainException {

    private Long deckId;

    public NonPublishedDeckException(Long deckId) {
        this.deckId = deckId;
    }

    public Long getDeckId() {
        return deckId;
    }
}
