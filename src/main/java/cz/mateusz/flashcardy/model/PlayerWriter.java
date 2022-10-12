package cz.mateusz.flashcardy.model;

public interface PlayerWriter {
    Player write(Player player);
    boolean erase(Player player);
}
