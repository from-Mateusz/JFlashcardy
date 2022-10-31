package cz.mateusz.flashcardy.model;

public class UnknownPlayerException extends DomainException {

    private Long playerId;

    private String email;

    public UnknownPlayerException(Long playerId) {
        this.playerId = playerId;
    }

    public UnknownPlayerException(String email) { this.email = email; }

    public Long getPlayerId() {
        return playerId;
    }
}
