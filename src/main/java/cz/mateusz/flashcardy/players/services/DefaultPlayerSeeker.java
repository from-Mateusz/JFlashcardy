package cz.mateusz.flashcardy.players.services;

import cz.mateusz.flashcardy.players.data.PlayerRepository;
import cz.mateusz.flashcardy.model.UnknownPlayerException;
import cz.mateusz.flashcardy.players.PlayerSeeker;
import cz.mateusz.flashcardy.players.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPlayerSeeker implements PlayerSeeker {

    private PlayerRepository playerRepository;

    public DefaultPlayerSeeker(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player seekPlayerById(Long playerId) throws UnknownPlayerException {
        Optional<Player> possiblePlayer = playerRepository.findById(playerId);
        if(!possiblePlayer.isPresent()) throw new UnknownPlayerException(playerId);
        return possiblePlayer.get();
    }

    @Override
    public Player seekPlayerByEmail(String email) throws UnknownPlayerException {
        Optional<Player> possiblePlayer = playerRepository.findByEmail(email);
        if(!possiblePlayer.isPresent()) throw new UnknownPlayerException(email);
        return possiblePlayer.get();
    }

    @Override
    public List<Player> seekAllPlayers() {
        return playerRepository.findAll();
    }
}
