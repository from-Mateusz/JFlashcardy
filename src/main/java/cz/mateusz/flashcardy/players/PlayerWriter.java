package cz.mateusz.flashcardy.players;

import cz.mateusz.flashcardy.players.model.Player;

public interface PlayerWriter {
    Player write(Player player);
    boolean erase(Player player);
}
