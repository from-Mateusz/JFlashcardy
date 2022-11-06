package cz.mateusz.flashcardy.players.data;

import cz.mateusz.flashcardy.players.security.PlayerPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerPermissionRepository extends MongoRepository<PlayerPermission, Long> {

}
