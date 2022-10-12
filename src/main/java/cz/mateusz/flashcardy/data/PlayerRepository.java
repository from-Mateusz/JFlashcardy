package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, Long> {
    @Query("{'email.address': ?0}")
    Optional<Player> findByEmail(String email);
}
