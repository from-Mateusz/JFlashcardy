package cz.mateusz.flashcardy.model;

public class UnknownDeckException extends DomainException {

    private Long deckId;

    public UnknownDeckException(Long deckId) {
        this.deckId = deckId;
    }

    public Long getDeckId() {
        return deckId;
    }
}
