package cz.mateusz.flashcardy.players.services;

import cz.mateusz.flashcardy.players.PlayerWriter;
import cz.mateusz.flashcardy.players.model.Player;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import cz.mateusz.flashcardy.players.data.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultPlayerWriter implements PlayerWriter {

    private PlayerRepository playerRepository;

    private NumericIdSequenceManager idSequenceManager;

    public DefaultPlayerWriter(PlayerRepository playerRepository, NumericIdSequenceManager idSequenceManager) {
        this.playerRepository = playerRepository;
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public Player write(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public boolean erase(Player player) {
        return false;
    }
}
