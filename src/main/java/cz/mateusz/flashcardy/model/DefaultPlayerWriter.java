package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.CollectionName;
import cz.mateusz.flashcardy.data.IdSequence;
import cz.mateusz.flashcardy.data.NumericIdSequenceManager;
import cz.mateusz.flashcardy.data.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
