package cz.mateusz.flashcardy.players.data;

import cz.mateusz.flashcardy.players.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, Long> {
    @Query("{'email.address': ?0}")
    Optional<Player> findByEmail(String email);
}
