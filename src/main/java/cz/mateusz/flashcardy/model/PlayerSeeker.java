package cz.mateusz.flashcardy.model;

import java.util.List;
import java.util.Optional;

public interface PlayerSeeker {
    Player seekPlayerById(Long playerId) throws UnknownPlayerException;

    Player seekPlayerByEmail(String email) throws UnknownPlayerException;

    List<Player> seekAllPlayers();
}
