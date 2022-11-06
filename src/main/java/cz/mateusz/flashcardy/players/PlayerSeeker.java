package cz.mateusz.flashcardy.players;

import cz.mateusz.flashcardy.model.UnknownPlayerException;
import cz.mateusz.flashcardy.players.model.Player;

import java.util.List;

public interface PlayerSeeker {
    Player seekPlayerById(Long playerId) throws UnknownPlayerException;

    Player seekPlayerByEmail(String email) throws UnknownPlayerException;

    List<Player> seekAllPlayers();
}
