package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.PlayerRepository;
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
        return null;
    }

    @Override
    public List<Player> seekAllPlayers() {
        return playerRepository.findAll();
    }
}
