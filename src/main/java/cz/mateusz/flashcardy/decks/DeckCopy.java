package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.players.model.Player;

public class DeckCopy {

    private Deck original;

    private Player copyOwner;

    public DeckCopy(Deck original, Player copyOwner) {
        this.original = original;
        this.copyOwner = copyOwner;
    }
}
